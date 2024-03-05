package org.example.accounts.domain;

public class AccountEmailMother {

  public static AccountEmail create(String value) {
    return new AccountEmail(value);
  }

  public static AccountEmail random() {
    return create("random");
  }
}
