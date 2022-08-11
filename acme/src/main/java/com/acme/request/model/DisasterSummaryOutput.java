package com.acme.request.model;

import com.acme.common.DisasterSummaryResult;
import lombok.Data;


@Data
public class DisasterSummaryOutput {
    private Long unaffected;
    private Long injured;
    private Long illInFacility;
    private Long illNotInFacility;
    private Long deceased;
    private Long isolated;

    public DisasterSummaryOutput() {
        this.setIsolated(0L);
        this.setDeceased(0L);
        this.setIllNotInFacility(0L);
        this.setIllInFacility(0L);
        this.setUnaffected(0L);
        this.setInjured(0L);
    }
}