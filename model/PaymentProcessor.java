package model;

import java.util.List;
import java.util.Scanner;

public class PaymentProcessor {
    private Scanner scanner = new Scanner(System.in);

    public void processPayment(PaymentStrategy paymentStrategy, double amount) {
        if (!paymentStrategy.validate(amount)) {
            throw new IllegalArgumentException("Transaksi Gagal: Saldo atau limit tidak mencukupi!");
        }

        List<AuthorizationStrategy> requiredAuthorizations = paymentStrategy.getRequiredAuthorizations();
        
        for (AuthorizationStrategy auth : requiredAuthorizations) {
            auth.authenticate(paymentStrategy);
        }

        paymentStrategy.pay(amount);
    }
}

