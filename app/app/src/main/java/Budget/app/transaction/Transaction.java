package Budget.app.transaction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.net.Proxy;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    private BigDecimal amount;
    private String description;
    private String category;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

}
