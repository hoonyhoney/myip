package com.address.myip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class MyipApplicationTests {

    @Test
    @DisplayName("ip vaild test")
    void ipValidTest() {
        String ip = "123.123.123.123";
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        boolean isValid = ip.matches(PATTERN);
        assertTrue("ip형식이 유효",isValid);
    }
}
