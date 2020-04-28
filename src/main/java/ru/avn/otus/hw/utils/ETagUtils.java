package ru.avn.otus.hw.utils;

public class ETagUtils {

    public static String intToETagHeader(int version) {
        return "\"" + version + "\"";
    }


    public static int ifMatchHeaderToInt(String ifMatchHeader) {
        return Integer.parseInt(ifMatchHeader.replace("\"", ""));
    }
}
