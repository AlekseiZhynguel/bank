package org.example.accounts.domain;

public class AccountDniMother {

    public static AccountDni create(String value) {
        return new AccountDni(value);
    }
    public static AccountDni random() {
        return create("random");
    }
}
