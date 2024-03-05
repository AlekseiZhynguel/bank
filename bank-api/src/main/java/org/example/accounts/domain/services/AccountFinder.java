package org.example.accounts.domain.services;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountRepository;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountFinder {

  private final AccountRepository repository;

  public AccountFinder(AccountRepository repository) {
    this.repository = repository;
  }

  public boolean existsById(AccountId id) {
    return repository.findById(id).isPresent();
  }
}
