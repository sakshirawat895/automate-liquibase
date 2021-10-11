package com.demo.automateliquibase.util;

public final class GenerateScriptConstants {
    public static final String INSERT= "insert";
    public static final String UPDATE= "update";
    public static final String CREATE_TABLE= "create_table";
    public static final String ALTER_TABLE= "alter_table";

    private GenerateScriptConstants(){
        throw new UnsupportedOperationException("Utility Class");
    }
}
