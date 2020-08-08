package cod.currency.util.enums;

/**
 * JMA - 8/7/2020 22:34
 **/
public enum CoinEnum {
    BTCEUR("btceur");

    String value;

    CoinEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
