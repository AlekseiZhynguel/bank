package org.example.payments.deposits.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositMother;
import org.example.payments.deposits.domain.DepositRepository;
import org.example.payments.deposits.domain.events.DepositCreated;
import org.example.payments.deposits.domain.events.DepositCreatedMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class DepositCreatorTest {

  private final AccountFinder finder = mock(AccountFinder.class);
  private final DepositRepository repository = mock(DepositRepository.class);
  private final EventBus eventBus = mock(EventBus.class);
  private final DepositCreator useCase = new DepositCreator(finder, repository, eventBus);

  @Test
  void shouldDepositFunds() {

    Deposit deposit = DepositMother.random();
    DepositCreated event = DepositCreatedMother.fromDeposit(deposit);

    when(finder.existsById(any())).thenReturn(true);

    useCase.deposit(
        deposit.id().value(),
        deposit.destinationAccount().value(),
        deposit.amount().value(),
        deposit.description().value());

    verify(repository).save(deposit);
    verify(eventBus).publish(Collections.singletonList(event));
  }
}
