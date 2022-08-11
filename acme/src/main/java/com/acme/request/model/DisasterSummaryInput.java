package com.acme.request.model;

import lombok.Data;


@Data
public class DisasterSummaryInput {
    /** The facility NPI, optional for govt user */
    private Long facilityNpi;
    /** Disaster ID, required */
    private Long disasterId;
    /** State ID, if provided filter by state, optional */
    private Integer stateId;
    /** Date, if provided filter up until the date, inclusive, optional */
    private String timeFrame;
    /** Status ID, if provided filter only by users with that status, optional */
    private Integer statusId;
}