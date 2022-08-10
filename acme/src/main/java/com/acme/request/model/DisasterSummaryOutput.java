package com.acme.request.model;

import lombok.Data;


@Data
public class DisasterSummaryOutput {
    private Long unaffected;
    private Long injured;
    private Long illInFacility;
    private Long illNotInFacility;
    private Long deceased;
    private Long isolated;
}