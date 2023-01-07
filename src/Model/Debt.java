package Model;

import java.math.BigDecimal;
import java.util.List;

public class Debt {
    int userId;
    String lenderName;
    BigDecimal initialAmount;
    BigDecimal interestRate;
    int termMonths;
    String date;
    BigDecimal paymentAmount;
    String paymentDate;
    int isRepaid;
    List<DebtPayment> debtPayments;

    public Debt(int userId, String lenderName, BigDecimal initialAmount, BigDecimal interestRate,
                int termMonths, String date, BigDecimal paymentAmount, String paymentDate, int isRepaid) {
        this.userId = userId;
        this.lenderName = lenderName;
        this.initialAmount = initialAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.isRepaid = isRepaid;
    }

    public Debt() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getIsRepaid() {
        return isRepaid;
    }

    public void setIsRepaid(int isRepaid) {
        this.isRepaid = isRepaid;
    }

    public List<DebtPayment> getDebtPayments() {
        return debtPayments;
    }

    public void setDebtPayments(List<DebtPayment> debtPayments) {
        this.debtPayments = debtPayments;
    }
}
