package com.hashan.silva.util;

public class Constant {
    public static final String AI = "ai/v2";
    public static final String GENERATE_HAIKU = "haiku";
    public static final String GENERATE_POETRY= "poetry";
    public static final String GENERATE_ANALYSIS= "analysis";
    public static final String WRITE_ME_HAIKU_ABOUT_CAT = """
           Write me Haiku about cat,
           haiku should start with the word cat obligatory""";


    private Constant() {
        throw new IllegalStateException("Utility Class");
    }
}
