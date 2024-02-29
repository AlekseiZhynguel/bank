package org.example.accounts.infrastructure.persistence;

import org.example.accounts.domain.Account;
import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountRepository;
import org.example.accounts.domain.primitives.AccountPrimitives;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class InMemoryAccountRepository implements AccountRepository {

    private Map<String, AccountPrimitives> accounts = new HashMap<>();
    @Override
    public void save(Account account) {
        AccountPrimitives primitives = account.toPrimitives();
        accounts.put(primitives.id(), primitives);
    }

    @Override
    public Optional<Account> findById(AccountId id) {
        return Optional.ofNullable(accounts.get(id.value())).map(Account::from);
    }
}
