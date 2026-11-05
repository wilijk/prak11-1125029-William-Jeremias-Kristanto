package model;

import java.util.List;

public class DebitCard implements PaymentStrategy{
    private String cardNumber;
    private double balance;
    private String pin;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public DebitCard(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
    }

    @Override 
    public boolean validate(double amount) {
        return balance >= amount;
    }

    @Override 
    public void pay(double amount) {
        if (validate(amount)) {
            balance -= amount;
            System.out.println("Pembayaran Debit berhasil, sisa Debit Card: " + balance);
        } else {
            System.out.println("Saldo tidak mencukupi!");
            System.out.println("Sisa saldo Debit Card: " + balance);
        }
    }

    @Override 
    public List<AuthorizationMethod> getRequiredAuthorizations(){
        return List.of(AuthorizationMethod.PIN);
    }
}
