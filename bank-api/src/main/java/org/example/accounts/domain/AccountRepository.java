package org.example.accounts.domain;

import java.util.Optional;

public interface AccountRepository {
  void save(Account account);

  Optional<Account> findById(AccountId id);
}
