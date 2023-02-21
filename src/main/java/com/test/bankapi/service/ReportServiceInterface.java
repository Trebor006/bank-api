package com.test.bankapi.service;

import com.test.bankapi.dto.ReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportServiceInterface {
    List<ReportDto> getMovementsByCustomerAndDateRange(
            String customerName,
            LocalDate startDate,
            LocalDate endDate
    );
}
