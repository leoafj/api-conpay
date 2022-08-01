package corretoracriptomoedas.service;

import corretoracriptomoedas.model.Wallet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface BitcoinService {

    BigDecimal bitcoinPrice(BigDecimal price);

    Wallet getWallet();

    BigDecimal depositMoney(@PathVariable BigDecimal money);

    BigDecimal balanceMoney();

    BigDecimal balanceBitcoin();

    Object walletCreate(@RequestBody Wallet wallet);

     Object walletDelete();

    void moneyToBitcoin();

    void bitcoinToMoney();

    BigDecimal cashOut(@PathVariable BigDecimal money);



}
