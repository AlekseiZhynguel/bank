package org.example.accounts.domain;

public class AccountPhoneMother {

  public static AccountPhone create(String value) {
    return new AccountPhone(value);
  }

  public static AccountPhone random() {
    return create("random");
  }
}
