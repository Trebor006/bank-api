package com.test.bankapi.controller;

import com.test.bankapi.constants.RestRequestMappingConstants;
import com.test.bankapi.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(RestRequestMappingConstants.REPORTS)
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<String>> getReports(
            @RequestParam("date") LocalDate date
    ) {
        var reports = reportService.getReports();
        log.info("getReports");

        return ResponseEntity.ok(reports);
    }

}
