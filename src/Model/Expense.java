package Model;

import java.math.BigDecimal;

public class Expense {
    int userId;
    BigDecimal amount;
    String date;
    String freq;
    String category;
    String desc;
    int id;

    public Expense(int userId, BigDecimal amount, String date, String freq, String category, String desc, int id) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.freq = freq;
        this.category = category;
        this.desc = desc;
        this.id = id;

    }

    public Expense() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
