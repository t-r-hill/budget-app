package Model;

import java.math.BigDecimal;

public class Income {
    int userId;
    BigDecimal amount;
    String freq;
    String date;
    String source;

    public Income(int userId, BigDecimal amount, String freq, String date, String source) {
        this.userId = userId;
        this.amount = amount;
        this.freq = freq;
        this.date = date;
        this.source = source;
    }

    public Income(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
