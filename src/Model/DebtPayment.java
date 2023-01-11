package Model;

import java.math.BigDecimal;

public class DebtPayment implements Transaction{
    int debtId;
    String date;
    BigDecimal amount;
    BigDecimal currentBalance;
    int id;

    public DebtPayment(int debtId, String date, BigDecimal amount, BigDecimal currentBalance, int id) {
        this.debtId = debtId;
        this.date = date;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.id = id;
    }

    public DebtPayment() {
    }

    public int getDebtId() {
        return debtId;
    }

    public void setDebtId(int debtId) {
        this.debtId = debtId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DebtPayment{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
