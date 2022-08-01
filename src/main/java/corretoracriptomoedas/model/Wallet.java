package corretoracriptomoedas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id = 1L;
    private String name;
    @Column(precision = 16, scale = 2)
    private BigDecimal moneyBalance;
    @Column(precision = 16, scale = 6)
    private BigDecimal moneyBitcoin;


    public Wallet(String name, BigDecimal moneyBalance, BigDecimal moneyBitcoin) {
        this.name = name;
        this.moneyBalance = moneyBalance;
        this.moneyBitcoin = moneyBitcoin;
    }



}
