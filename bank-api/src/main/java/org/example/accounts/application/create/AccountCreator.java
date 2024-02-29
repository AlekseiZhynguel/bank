package org.example.accounts.application.create;

import org.example.accounts.domain.Account;
import org.example.accounts.domain.AccountRepository;
import org.example.domain.EventBus;
import org.springframework.stereotype.Service;

@Service
public class AccountCreator {

    private final AccountRepository repository;
    private final EventBus eventBus;

    public AccountCreator(AccountRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(String id, String name, String email, String phone, String dni) {
        Account account = Account.create(id, name, email, phone, dni);

        repository.save(account);
        eventBus.publish(account.pullDomainEvents());
    }
}
