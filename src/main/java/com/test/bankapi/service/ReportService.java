package com.test.bankapi.service;

import com.test.bankapi.dto.ReportDto;
import com.test.bankapi.enums.AccountType;
import com.test.bankapi.enums.StatusEnum;
import com.test.bankapi.repository.MovementRepository;
import com.test.bankapi.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportServiceInterface {

    private final MovementRepository movementRepository;

    @Override
    public List<ReportDto> getMovementsByCustomerAndDateRange(
            String customerName,
            LocalDate startDate,
            LocalDate endDate
    ) {
        var records = movementRepository.getMovementsByCustomerAndDateRange(
                customerName,
                DateUtils.getStartDate(DateUtils.parse(startDate)),
                DateUtils.getEndDate(DateUtils.parse(endDate))
        );

        return records.stream()
                .map(this::parseRecord)
                .collect(Collectors.toList());
    }

    private ReportDto parseRecord(Object[] record) {
        return ReportDto.builder()
                .date((LocalDateTime) record[0])
                .customerName((String) record[1])
                .accountNumber((String) record[2])
                .accountType(((AccountType) record[3]).name())
                .accountInitialBalance((Double) record[4])
                .accountStatus(((StatusEnum) record[5]).name())
                .movementValue((Double) record[6])
                .movementBalance((Double) record[7])
                .build();
    }
}
