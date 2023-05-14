package io.bookquest.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TestUtil {

    public static String generateString() {
        return RandomStringUtils.random(10, true, false);
    }

}
