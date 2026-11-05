package model;

import java.util.List;

public class CreditCard implements PaymentStrategy{
    String cardNumber;
    double limit;
    double currentDebt;
    String ccv;
    String pin;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(double currentDebt) {
        this.currentDebt = currentDebt;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public CreditCard(String cardNumber, double limit, String ccv, String pin) {
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.currentDebt = 0;
        this.ccv = ccv;
        this.pin = pin;
    }

    @Override 
    public boolean validate(double amount) {
        return currentDebt + amount <= limit;
    }

    @Override 
    public void pay(double amount) {
        if (validate(amount)) {
            currentDebt += amount;
            limit -= amount;
            System.out.println("Pembayaran Credit berhasil, sisa limit Credit Card: " + limit);
        } else {
            System.out.println("Pembayaran gagal!");
            System.out.println("Sisa limit Credit Card: " + limit);
        }
    }
 
    @Override 
    public List<AuthorizationMethod> getRequiredAuthorizations(){
        return List.of(AuthorizationMethod.CCV, AuthorizationMethod.PIN);
    } 
}

