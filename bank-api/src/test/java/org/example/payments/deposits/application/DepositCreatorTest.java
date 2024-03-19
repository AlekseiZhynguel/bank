package org.example.payments.deposits.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.infrastructure.events.noop.EventBusNoop;
import org.example.payments.deposits.controller.dto.CreateDepositRequestMother;
import org.example.payments.deposits.domain.DepositIdMother;
import org.example.payments.deposits.domain.DepositMother;
import org.example.payments.deposits.infrastructure.persistence.InMemoryDepositRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DepositCreatorTest {

  @Test
  void shouldCreateDeposit() {

    var finder = mock(AccountFinder.class);
    var repository = new InMemoryDepositRepository();
    var eventBus = new EventBusNoop();
    var useCase = new DepositCreator(finder, repository, eventBus);

    var id = DepositIdMother.random();
    var request = CreateDepositRequestMother.random();
    var deposit = DepositMother.fromRequest(id, request);

    when(finder.existsById(new AccountId(request.destinationAccount()))).thenReturn(true);

    useCase.deposit(deposit.id().value(), request);

    var expected = repository.findById(id).get();

    assertEquals(expected, deposit);
  }
}
