package com.aninfo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
    WITHDRAW("WITHDRAW"),
    DEPOSIT("DEPOSIT");

    private String typeName;

    TransactionType(String typeName) {
        this.typeName = typeName;
    }

    @JsonValue
    public String getTypeName() {
        return typeName;
    }
}
