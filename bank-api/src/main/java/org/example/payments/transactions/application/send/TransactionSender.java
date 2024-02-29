package org.example.payments.transactions.application.send;

import org.example.domain.EventBus;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionSender {
    private final EventBus eventBus;
    private final TransactionRepository repository;

    public TransactionSender(EventBus eventBus, TransactionRepository repository) {
        this.eventBus = eventBus;
        this.repository = repository;
    }

    public void send(String id, String originAccount, String destinationAccount, Integer amount, String description) {

        Transaction transaction = Transaction.create(id, originAccount, destinationAccount, amount, description);

        repository.save(transaction);
        eventBus.publish(transaction.pullDomainEvents());
    }
}
