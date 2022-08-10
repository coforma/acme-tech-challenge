package com.acme.request.model;

import lombok.Data;


@Data
public class DisasterSummaryOutput {
    private Integer unaffected;
    private Integer injured;
    private Integer illInFacility;
    private Integer illNotInFacility;
    private Integer deceased;
    private Integer isolated;
}