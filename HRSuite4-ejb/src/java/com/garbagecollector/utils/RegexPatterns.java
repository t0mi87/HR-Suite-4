package com.garbagecollector.utils;


public interface RegexPatterns {

    String REGEX_LAST_NAME = "^[A-Za-z]{2,}";

    String REGEX_FIRST_NAME = "^[A-Za-z]{2,}";

    String REGEX_PHONE_NUMBER = "^[0-9]+\\.[0-9]+\\.[0-9]+";

    String REGEX_COMMISSION = "^[0]{1}\\.[0-9]{1,2}";
}

