package corretoracriptomoedas.controller;

import corretoracriptomoedas.model.Wallet;
import corretoracriptomoedas.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("exchange")
public class ExchangeController {

    private static final long ID = 1L;

    @Autowired
    private BitcoinService bitcoinService;

    @GetMapping("/bitcoin-price")
    public BigDecimal bitcoinPrice(BigDecimal price) {
        return bitcoinService.bitcoinPrice(price);
    }

    @GetMapping("/get-wallet")
    public Wallet getWallet() {
        return bitcoinService.getWallet();
    }

    @PutMapping("/wallet/deposit/{money}")
    public BigDecimal depositMoney(@PathVariable BigDecimal money) {
       return bitcoinService.depositMoney(money);
    }

    @GetMapping("/wallet/balance-money")
    public BigDecimal balanceMoney() {
        return bitcoinService.balanceMoney();
    }

    @GetMapping("/wallet/balance-bitcoin")
    public BigDecimal balanceBitcoin() {
        return bitcoinService.balanceBitcoin();
    }

    @PostMapping("/wallet/create")
    public Object walletCreate(@RequestBody Wallet wallet) {
        return bitcoinService.walletCreate(wallet);
    }

    @DeleteMapping("/wallet/delete")
    public Object walletDelete() {
        return bitcoinService.walletDelete();
    }

    @PostMapping("/wallet/money-to-bitcoin")
    public void moneyToBitcoin(){
        bitcoinService.moneyToBitcoin();
    }

    @PostMapping("wallet/bitcoin-to-money")
    public void bitcoinToMoney() {
        bitcoinService.bitcoinToMoney();
    }

    @PutMapping("/wallet/cashout/{money}")
    BigDecimal cashOut(@PathVariable BigDecimal money) {
        return bitcoinService.cashOut(money);
    }



}
