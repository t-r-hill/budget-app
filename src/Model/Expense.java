package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Expense {
    int userId;
    BigDecimal amount;
    String date;
    String freq;
    String category;
    String desc;
    int id;

    public static List<String> categories = new ArrayList<>(
            List.of(
                    "rent",
                    "groceries",
                    "eating out",
                    "alcohol",
                    "childcare",
                    "healthcare",
                    "insurance",
                    "petrol",
                    "utilities",
                    "internet/TV",
                    "transport",
                    "holiday",
                    "mobile phone",
                    "leisure",
                    "gifts"
    ));

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

    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", date='" + date + '\'' +
                ", freq='" + freq + '\'' +
                ", category='" + category + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
