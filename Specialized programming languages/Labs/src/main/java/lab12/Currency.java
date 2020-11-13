package lab12;

import java.util.StringJoiner;

public class Currency {
    private String baseCurrency;
    private String currency;
    private String saleRateNB;
    private String purchaseRateNB;
    private String saleRate;
    private String purchaseRate;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(String saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public String getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(String purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
    }

    public String getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(String purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Currency.class.getSimpleName() + "[", "]")
                .add("baseCurrency='" + baseCurrency + "'")
                .add("currency='" + currency + "'")
                .add("saleRateNB='" + saleRateNB + "'")
                .add("purchaseRateNB='" + purchaseRateNB + "'")
                .add("saleRate='" + saleRate + "'")
                .add("purchaseRate='" + purchaseRate + "'")
                .toString();
    }
}
