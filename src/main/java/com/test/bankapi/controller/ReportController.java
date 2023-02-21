package com.test.bankapi.controller;

import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.SuccessApiResponseServiceInterface;
import com.test.bankapi.constants.RestRequestMappingConstants;
import com.test.bankapi.service.ReportServiceInterface;
import com.test.bankapi.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(RestRequestMappingConstants.REPORTS)
public class ReportController {

    private final ReportServiceInterface reportService;
    private final SuccessApiResponseServiceInterface successApiResponseService;

    @GetMapping
    public ResponseEntity<ApiResponse> getReports(
            @RequestParam("customerName") String customerName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        var reports = reportService.getMovementsByCustomerAndDateRange(
                customerName,
                DateUtils.toLocalDate(startDate),
                DateUtils.toLocalDate(endDate)
        );
        log.info("getReports");

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(reports));
    }

}
