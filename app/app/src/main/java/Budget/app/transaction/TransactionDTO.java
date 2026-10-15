package Budget.app.transaction;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {
    public String name;
    public LocalDate date;
    public BigDecimal amount;
    public String description;
    public String category;
    @Enumerated(EnumType.STRING)
    public TransactionType type;
}
