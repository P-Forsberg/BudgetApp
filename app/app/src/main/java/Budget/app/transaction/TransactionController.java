package Budget.app.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO dto){
        try {
            return ResponseEntity.ok(transactionService.addTransaction(dto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<Transaction> getTransactions(@RequestParam(required = false) TransactionType type){
        return transactionService.getTransactions(type);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable long id, @RequestBody TransactionDTO dto){
        return transactionService.updateTransaction(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();

    }
}
