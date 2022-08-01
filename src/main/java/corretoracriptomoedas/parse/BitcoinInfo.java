package corretoracriptomoedas.parse;

import corretoracriptomoedas.model.Bitcoin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "bitcoin", url = "https://www.mercadobitcoin.net/api/BTC/ticker/")
public interface BitcoinInfo {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    Bitcoin info();
}
