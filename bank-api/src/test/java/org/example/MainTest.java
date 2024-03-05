package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void test() {
    assertThat(1).isBetween(0, 2);
  }
}
