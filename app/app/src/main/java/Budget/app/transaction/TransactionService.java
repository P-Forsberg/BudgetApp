package Budget.app.transaction;

import Budget.app.transaction.Exceptions.TransactionNotfound;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {

    public String description;
    public String category;

    private final TransactionRepository transactionRepository;

    public @Nullable Object addTransaction(TransactionDTO dto) {
        if(dto.name == null || dto.name.isBlank()){
            throw new RuntimeException("Name can't be empty");
        }
        if(dto.amount == null || dto.amount.compareTo(BigDecimal.ZERO) == 0){
            throw new RuntimeException("The amount can't be zero");
        }
        if(dto.category == null || dto.category.isBlank()){
            throw new RuntimeException("Please choose a category between these three:");
        }
        if (dto.type == null) {
            throw new RuntimeException("Choose between EXPENSE or INCOME");
        }


        Transaction transaction = new Transaction();
        transaction.setName(dto.name);
        transaction.setAmount(dto.amount);
        transaction.setCategory(dto.category);
        transaction.setDescription(dto.description);
        transaction.setDate(dto.date);
        transaction.setType(dto.type);
        transactionRepository.save(transaction);
        return transaction;
    }

    public List<Transaction> getTransactions(TransactionType type) {
        if(type == null){
            return transactionRepository.findAll();
        }
        return transactionRepository.findByType(type);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionNotfound(
                                "Id: " + id + " Finns inte inlagt"
                        )
                );
    }

    public Transaction updateTransaction(TransactionDTO dto, long id) {
        Transaction transaction = getTransactionById(id);
        transaction.setName(dto.name);
        transaction.setAmount(dto.amount);
        transaction.setCategory(dto.category);
        transaction.setDescription(dto.description);
        transaction.setDate(dto.date);
        transaction.setType(dto.type);
        transactionRepository.save(transaction);
        return transaction;
    }

    public void deleteTransaction(long id) {
        getTransactionById(id);
        transactionRepository.deleteById(id);
    }
}