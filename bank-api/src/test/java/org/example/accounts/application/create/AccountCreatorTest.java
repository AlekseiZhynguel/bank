package org.example.accounts.application.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.accounts.domain.AccountIdMother;
import org.example.accounts.domain.AccountMother;
import org.example.accounts.domain.exceptions.AccountIdNotUuid;
import org.example.accounts.infrastructure.controller.dto.AccountCreateRequestMother;
import org.example.accounts.infrastructure.persistence.InMemoryAccountRepository;
import org.example.infrastructure.events.noop.EventBusNoop;
import org.junit.jupiter.api.Test;

class AccountCreatorTest {

  @Test
  void shouldCreateAnAccount() {

    var repository = new InMemoryAccountRepository();
    var eventBus = new EventBusNoop();
    var useCase = new AccountCreator(repository, eventBus);
    var id = AccountIdMother.random();
    var request = AccountCreateRequestMother.random();
    var account = AccountMother.fromRequest(id, request);

    useCase.create(id.value(), request);

    var expected = repository.findById(id).get();
    assertEquals(expected, account);
  }

  @Test
  void shouldThrowException() {

    assertThrows(
        AccountIdNotUuid.class,
        () -> {
          var repository = new InMemoryAccountRepository();
          var eventBus = new EventBusNoop();
          var useCase = new AccountCreator(repository, eventBus);
          var id = AccountIdMother.withInvalidId();
          var request = AccountCreateRequestMother.random();

          useCase.create(id.value(), request);
        });
  }
}
