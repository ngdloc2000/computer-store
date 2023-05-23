package com.cdtn.computerstore.repository.transactions;

import com.cdtn.computerstore.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ITransactionsRepository extends JpaRepository<Transactions, UUID> {
}
