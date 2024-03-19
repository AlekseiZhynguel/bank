package org.example.accounts.domain;

import org.example.accounts.domain.primitives.AccountPrimitives;
import org.example.accounts.infrastructure.controller.put.dto.AccountCreateRequest;

public class AccountMother {

  public static Account create(
      AccountId id, AccountName name, AccountEmail email, AccountPhone phone, AccountDni dni) {
    return Account.from(
        new AccountPrimitives(id.value(), name.value(), email.value(), phone.value(), dni.value()));
  }

  public static Account random() {
    return create(
        AccountIdMother.random(),
        AccountNameMother.random(),
        AccountEmailMother.random(),
        AccountPhoneMother.random(),
        AccountDniMother.random());
  }

  public static Account withInvalidId() {
    return create(
        AccountIdMother.create("invalid"),
        AccountNameMother.random(),
        AccountEmailMother.random(),
        AccountPhoneMother.random(),
        AccountDniMother.random());
  }

  public static Account fromRequest(AccountId id, AccountCreateRequest request) {
    return Account.from(
        new AccountPrimitives(
            id.value(), request.name(), request.email(), request.phone(), request.dni()));
  }
}
