package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void test() {
        assertThat(1).isBetween(0, 2);
    }

}