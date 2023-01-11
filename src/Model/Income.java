package Model;

import java.math.BigDecimal;

public class Income implements Transaction{
    int userId;
    BigDecimal amount;
    String freq;
    String date;
    String source;
    int id;

    public Income(int userId, BigDecimal amount, String freq, String date, String source, int id) {
        this.userId = userId;
        this.amount = amount;
        this.freq = freq;
        this.date = date;
        this.source = source;
        this.id = id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
