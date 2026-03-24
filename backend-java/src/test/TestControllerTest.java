package com.lypu.support_app.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestControllerTest {

    @Test
    void testMethodReturnsCorrectValue() {
        TestController controller = new TestController();

        String result = controller.test();

        assertEquals("Hello from backend!", result);
    }
}