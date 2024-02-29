package org.example.accounts.application.create;

import org.example.accounts.domain.Account;
import org.example.accounts.domain.AccountMother;
import org.example.accounts.domain.AccountRepository;
import org.example.accounts.domain.events.AccountCreated;
import org.example.accounts.domain.events.AccountCreatedMother;
import org.example.domain.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountCreatorTest {
    private final AccountRepository repository = mock(AccountRepository.class);
    private final EventBus eventBus = mock(EventBus.class);
    private final AccountCreator creator = new AccountCreator(repository, eventBus);

    @Test
    void shouldCreateAnAccount() {

        Account account = AccountMother.random();
        AccountCreated event = AccountCreatedMother.fromAccount(account);

        creator.create(
                account.id().value(),
                account.name().value(),
                account.email().value(),
                account.phone().value(),
                account.dni().value()
        );

        verify(repository).save(account);
        verify(eventBus).publish(Collections.singletonList(event));
    }

}