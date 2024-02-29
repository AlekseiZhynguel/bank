package org.example.payments.transactions.application.send;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionSender {

    private final AccountFinder finder;
    private final EventBus eventBus;
    private final TransactionRepository repository;

    public TransactionSender(AccountFinder finder, EventBus eventBus, TransactionRepository repository) {
        this.finder = finder;
        this.eventBus = eventBus;
        this.repository = repository;
    }

    public void send(String id, String originAccount, String destinationAccount, Integer amount, String description) {

        if (bothAccountsExists(originAccount, destinationAccount)) {
            Transaction transaction = Transaction.create(id, originAccount, destinationAccount, amount, description);

            repository.save(transaction);
            eventBus.publish(transaction.pullDomainEvents());
        }
    }

    private boolean bothAccountsExists(String origin, String destination) {
        return finder.existsById(new AccountId(origin)) && finder.existsById(new AccountId(destination));
    }
}
