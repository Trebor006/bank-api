package com.test.bankapi.service;

import com.test.bankapi.component.messages.MessageServiceInterface;
import com.test.bankapi.dto.business.MovementLogicDto;
import com.test.bankapi.entity.Account;
import com.test.bankapi.entity.Movement;
import com.test.bankapi.enums.MovementType;
import com.test.bankapi.exception.AccountDebitMaxExcededException;
import com.test.bankapi.exception.MovementNoAllowedException;
import com.test.bankapi.exception.MovementNotFoundException;
import com.test.bankapi.repository.MovementRepository;
import com.test.bankapi.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MovementService implements MovementServiceInterface {

    private final MessageServiceInterface messageService;
    private final MovementRepository movementRepository;
    private final AccountServiceInterface accountService;

    private static boolean validateDebitTypeAndBalanceAllowed(MovementLogicDto movementDto,
                                                              Account account) {
        return movementDto.getType().equals(MovementType.DEBIT)
                && (account.getBalance() == 0 || Math.abs(movementDto.getValue()) > account.getBalance());
    }

    @Override
    public List<Movement> getMovements() {
        var movements = movementRepository.findAll();

        return movements;
    }

    @Override
    public Movement getMovementById(Long id) {
        var movement = movementRepository.findById(id)
                .orElseThrow(() -> new MovementNotFoundException(id));

        return movement;
    }

    @Override
    @Transactional
    public Movement createMovement(MovementLogicDto movementDto) {
        var account = accountService.getAccountById(movementDto.getAccountId());

        if (validateDebitTypeAndBalanceAllowed(movementDto, account)) {
            throw new RuntimeException(messageService.getMessage("movement.not.allowed.insufficient.balance"));
        }

        if ((movementDto.getType().equals(MovementType.CREDIT) && movementDto.getValue() <= 0) ||
                (movementDto.getType().equals(MovementType.DEBIT) && movementDto.getValue() >= 0)) {
            throw new MovementNoAllowedException(messageService.getMessage("movement.not.allowed"));
        }

        if (movementDto.getType().equals(MovementType.DEBIT)
                && !validateLimitDebit(movementDto, account)) {
            throw new AccountDebitMaxExcededException(messageService.getMessage("max.daily.debit.amount.exception"));
        }

        var newBalance = getNewBalance(movementDto, account.getBalance());

        var movement = Movement.builder()
                .date(movementDto.getDate())
                .type(movementDto.getType())
                .value(movementDto.getValue())
                .balance(newBalance)
                .account(account)
                .build();

        movementRepository.saveAndFlush(movement);

        account.setBalance(newBalance);
        accountService.updateAccount(account);

        return movement;
    }

    private boolean validateLimitDebit(MovementLogicDto movementDto,
                                       Account account) {
        List<Movement> movementsByDate = getMovementsByDate(movementDto);
        List<Movement> debitMovements = movementsByDate.stream()
                .filter(movement -> movement.getType().equals(MovementType.DEBIT))
                .collect(Collectors.toList());

        double totalDebits = debitMovements.stream().mapToDouble(Movement::getValue).sum()
                + movementDto.getValue();

        return Math.abs(totalDebits) <= account.getMaxDailyDebitAmount();
    }

    private List<Movement> getMovementsByDate(MovementLogicDto movementDto) {
        LocalDateTime startDate = DateUtils.getStartDate(movementDto.getDate());
        LocalDateTime endDate = DateUtils.getEndDate(movementDto.getDate());

        return movementRepository.findByDates(startDate, endDate);
    }


    private Double getNewBalance(MovementLogicDto movement, Double currentBalance) {
        return currentBalance + movement.getValue();
    }

    @Override
    @Transactional
    public Movement updateMovement(Long id, MovementLogicDto movementDto) {
        var account = accountService.getAccountById(movementDto.getAccountId());

        var newBalance = getNewBalance(movementDto, account.getBalance());

        var movement = Movement.builder()
                .date(movementDto.getDate())
                .type(movementDto.getType())
                .value(movementDto.getValue())
                .balance(newBalance)
                .account(account)
                .build();

        movementRepository.saveAndFlush(movement);

        account.setBalance(newBalance);
        accountService.updateAccount(account);

        return movement;
    }

    @Override
    @Transactional
    public void deleteMovement(Long id) {
        //todo Cambiar el eliminar fisico por un cambio de estado
        movementRepository.deleteById(id);
    }
}
