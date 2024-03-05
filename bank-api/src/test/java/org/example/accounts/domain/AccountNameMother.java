package org.example.accounts.domain;

public class AccountNameMother {

  public static AccountName create(String value) {
    return new AccountName(value);
  }

  public static AccountName random() {
    return create("random");
  }
}
