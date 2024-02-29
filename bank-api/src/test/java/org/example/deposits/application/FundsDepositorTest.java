package org.example.deposits.application;

import org.example.accounts.domain.services.AccountFinder;
import org.example.deposits.domain.DepositMother;
import org.example.deposits.domain.events.FundsDepositedMother;
import org.example.domain.EventBus;
import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositRepository;
import org.example.payments.deposits.domain.events.FundsDeposited;
import org.example.payments.deposits.application.FundsDepositor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FundsDepositorTest {

    private final AccountFinder finder = mock(AccountFinder.class);
    private final DepositRepository repository = mock(DepositRepository.class);
    private final EventBus eventBus = mock(EventBus.class);
    private final FundsDepositor useCase = new FundsDepositor(finder, repository, eventBus);

    @Test
    void shouldDepositFunds() {

        Deposit deposit = DepositMother.random();
        FundsDeposited event = FundsDepositedMother.fromDeposit(deposit);

        when(finder.existsById(any())).thenReturn(true);

        useCase.deposit(
                deposit.id().value(),
                deposit.destinationAccount().value(),
                deposit.amount().value(),
                deposit.description().value()
        );

        verify(repository).save(deposit);
        verify(eventBus).publish(Collections.singletonList(event));
    }
}