package model;

import java.util.List;

public class Wallet implements PaymentStrategy {
    private String name;
    private double cashValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCashValue() {
        return cashValue;
    }

    public void setCashValue(double cashValue) {
        this.cashValue = cashValue;
    }

    public Wallet(String name, double cashValue) {
        this.name = name;
        this.cashValue = cashValue;
    }

    @Override
    public boolean validate(double amount) {
        return cashValue >= amount;
    }

    @Override
    public void pay(double amount) {
        if (validate(amount)) {
            cashValue -= amount;
            System.out.println("Pembayaran cash berhasil, sisa wallet: " + cashValue);
        } else {
            System.out.println("Saldo tidak mencukupi!");
            System.out.println("Sisa saldo: " + cashValue);
        }
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of();
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 100 && amount <= 1000000;
    }

}
