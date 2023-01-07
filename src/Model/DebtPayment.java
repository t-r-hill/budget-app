package Model;

import java.math.BigDecimal;

public class DebtPayment {
    int debtId;
    String date;
    BigDecimal amount;
    BigDecimal currentBalance;

    public DebtPayment(int debtId, String date, BigDecimal amount, BigDecimal currentBalance) {
        this.debtId = debtId;
        this.date = date;
        this.amount = amount;
        this.currentBalance = currentBalance;
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
}
