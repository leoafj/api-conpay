package corretoracriptomoedas.service.Impl;

import corretoracriptomoedas.model.Wallet;
import corretoracriptomoedas.parse.BitcoinInfo;
import corretoracriptomoedas.repository.WalletRepository;
import corretoracriptomoedas.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class BitcoinServiceImpl implements BitcoinService {

    private static final long ID = 1L;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BitcoinInfo bitcoinInfo;


    public Wallet getWallet() {
        Optional<Wallet> optionalWallet = walletRepository.findById(ID);
        if (optionalWallet.isPresent()) {
            return optionalWallet.get();
        } else {
            walletRepository.save(new Wallet("Conpay", BigDecimal.valueOf(0.0).setScale(2, RoundingMode.HALF_EVEN),
                    BigDecimal.valueOf(0.0).setScale(6, RoundingMode.HALF_EVEN)));

            return walletRepository.findById(ID).get();
        }
    }

    public BigDecimal bitcoinPrice(BigDecimal price) {
        price = bitcoinInfo.info().getTicker().getLast();
        return price;
    }



    public BigDecimal depositMoney(@PathVariable BigDecimal money) {
        Wallet wallet = getWallet();
        BigDecimal moneyBalance = wallet.getMoneyBalance();
        wallet.setMoneyBalance(moneyBalance.add(money));
        walletRepository.save(wallet);

        return money;
    }

    public BigDecimal balanceMoney() {
        return getWallet().getMoneyBalance();
    }

    public BigDecimal balanceBitcoin() {
        return getWallet().getMoneyBitcoin();
    }

    public Object walletCreate(@RequestBody Wallet wallet) {
        walletRepository.save(wallet);

        return wallet;
    }


    public Object walletDelete() {
        walletRepository.deleteAll();
        return null;
    }


    public void moneyToBitcoin() {
        Wallet wallet = getWallet();
        BigDecimal balanceMoney = wallet.getMoneyBalance();
        BigDecimal bitcoinPrice = BigDecimal.valueOf(Double.valueOf(String.valueOf(bitcoinInfo.info().getTicker().getLast())));
        wallet.setMoneyBalance(BigDecimal.valueOf(0.0));
        wallet.setMoneyBitcoin(balanceMoney.divide(bitcoinPrice, 6, RoundingMode.HALF_EVEN));
        walletRepository.save(wallet);
    }


    public void bitcoinToMoney() {
        Wallet wallet = getWallet();
        BigDecimal balanceBitcoin = wallet.getMoneyBitcoin();
        BigDecimal bitcoinPrice = BigDecimal.valueOf(Double.valueOf(String.valueOf(bitcoinInfo.info().getTicker().getLast())));
        wallet.setMoneyBitcoin(BigDecimal.valueOf(0.0));
        wallet.setMoneyBalance(balanceBitcoin.multiply((bitcoinPrice).setScale(2, RoundingMode.HALF_EVEN)));
        walletRepository.save(wallet);
    }

    public BigDecimal cashOut(@PathVariable BigDecimal money) {
        Wallet wallet = getWallet();
        BigDecimal moneyBalance = wallet.getMoneyBalance();
        wallet.setMoneyBalance(moneyBalance.subtract(money));
        walletRepository.save(wallet);

        return money;
    }



}
