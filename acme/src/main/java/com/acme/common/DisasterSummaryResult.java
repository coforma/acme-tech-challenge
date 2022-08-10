package com.acme.common;

import lombok.Data;

@Data
public class DisasterSummaryResult {
    private String status;
    private Long total;

    public DisasterSummaryResult(String status, Long total) {
        this.status = status;
        this.total = total;
    }
}
