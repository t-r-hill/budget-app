package Model;

import Data.ReadData;

import java.math.BigDecimal;
import java.util.Map;

public class Debt implements Transaction{
    int userId;
    String lenderName;
    BigDecimal initialAmount;
    BigDecimal interestRate;
    int termMonths;
    String date;
    BigDecimal paymentAmount;
    String paymentDate;
    int isRepaid;
    Map<Integer, DebtPayment> debtPayments;
    int id;

    public Debt(int userId, String lenderName, BigDecimal initialAmount, BigDecimal interestRate,
                int termMonths, String date, BigDecimal paymentAmount, String paymentDate, int isRepaid, int id) {
        this.userId = userId;
        this.lenderName = lenderName;
        this.initialAmount = initialAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.isRepaid = isRepaid;
        this.id = id;
    }

    public Debt() {
    }

    public void loadDebtPayments(){
        ReadData readData = new ReadData();
        debtPayments = readData.readDebtPayments(this.id);
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

    public Map<Integer, DebtPayment> getDebtPayments() {
        return debtPayments;
    }

    public void setDebtPayments(Map<Integer, DebtPayment> debtPayments) {
        this.debtPayments = debtPayments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
