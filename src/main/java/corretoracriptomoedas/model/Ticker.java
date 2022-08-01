package corretoracriptomoedas.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Ticker {
    private String high;
    private String low;
    private String vol;
    private BigDecimal last;
    private String buy;
    private String sell;
    private String open;
    private Long date;
}
