package com.flexpag.paymentscheduler.models.enums;

public enum PaymentStatus {

    PENDING(1),
    PAID(2);

    private final Integer enumCode;

    private PaymentStatus(Integer enumCode) {
        this.enumCode = enumCode;
    }

    public Integer getEnumCode() {
        return enumCode;
    }

    public static PaymentStatus valueOf(Integer enumCode) {
        for (PaymentStatus value : PaymentStatus.values()) {
            if (value.getEnumCode() == enumCode) {
                return value;
            }
        }
        throw new IllegalArgumentException("Error: status code doesn't match with 'enumCode' values");
    }

}
