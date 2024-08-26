package main.java.com.example.buffr.controller;

import main.java.com.example.buffr.entity.Transaction;
import main.java.com.example.buffr.service.TransactionService;
import main.java.com.example.buffr.entity.User;
import main.java.com.example.buffr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String showAddTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "addTransaction";
    }

    @PostMapping("/add")
    public String addTransaction(Transaction transaction, Principal principal) {
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        transaction.setUser(user);
        transaction.setDate(LocalDate.now());
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions/view";
    }

    @GetMapping("/view")
    public String viewTransactions(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        List<Transaction> transactions = transactionService.getTransactionsByUser(user);
        model.addAttribute("transactions", transactions);
        return "viewTransactions";
    }
}