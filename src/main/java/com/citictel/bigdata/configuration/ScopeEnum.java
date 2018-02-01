package com.citictel.bigdata.configuration;

public enum ScopeEnum {
    READ("read", "Read all"),
    WRITE("write", "Write all"),
    TEST("test", "Scope for testing")
    ;

    private final Object[] values;


    ScopeEnum(Object... vals) {
        this.values = vals;
    }

    public String NAME() {
        return (String) values[0];
    }

    public String DESC() {
        return (String) values[1];
    }
}
