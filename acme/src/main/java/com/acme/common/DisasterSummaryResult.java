package com.acme.common;

import java.io.Serializable;

public class DisasterSummaryResult implements Serializable {
    private String status;
    private Long total;

    public DisasterSummaryResult(String status, Long total) {
        this.status = status;
        this.total = total;
    }
}
