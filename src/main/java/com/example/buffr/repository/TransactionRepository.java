package main.java.com.example.buffr.repository;

import main.java.com.example.buffr.entity.Transaction;
import main.java.com.example.buffr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}