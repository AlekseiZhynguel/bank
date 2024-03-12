package org.example.accounts.domain;

import java.util.Objects;
import org.example.accounts.domain.events.AccountCreated;
import org.example.accounts.domain.primitives.AccountPrimitives;
import org.example.domain.AggregateRoot;

public class Account extends AggregateRoot {

  private final AccountId id;
  private final AccountName name;
  private final AccountEmail email;
  private final AccountPhone phone;
  private final AccountDni dni;

  private Account(String id, String name, String email, String phone, String dni) {
    this.id = new AccountId(id);
    this.name = new AccountName(name);
    this.email = new AccountEmail(email);
    this.phone = new AccountPhone(phone);
    this.dni = new AccountDni(dni);
  }

  public static Account create(String id, String name, String email, String phone, String dni) {
    Account account = new Account(id, name, email, phone, dni);

    account.record(new AccountCreated(id));

    return account;
  }

  public static Account from(AccountPrimitives primitives) {
    return new Account(
        primitives.id(),
        primitives.name(),
        primitives.email(),
        primitives.phone(),
        primitives.dni());
  }

  public AccountPrimitives toPrimitives() {
    return new AccountPrimitives(
        id.value(), name().value(), email().value(), phone().value(), dni().value());
  }

  public AccountId id() {
    return id;
  }

  public AccountName name() {
    return name;
  }

  public AccountEmail email() {
    return email;
  }

  public AccountPhone phone() {
    return phone;
  }

  public AccountDni dni() {
    return dni;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(id, account.id)
        && Objects.equals(name, account.name)
        && Objects.equals(email, account.email)
        && Objects.equals(phone, account.phone)
        && Objects.equals(dni, account.dni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, phone, dni);
  }
}
