package ru.avn.otus.hw.common.web.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ETagUtils {

    public static String intToETagHeader(int version) {
        return version + "";
    }


    public static int ifMatchHeaderToInt(String ifMatchHeader) {
        return Integer.parseInt(ifMatchHeader.replace("\"", ""));
    }
}
