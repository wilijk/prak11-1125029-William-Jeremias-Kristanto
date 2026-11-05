package model;

import java.util.Scanner;

public class PinAuthorization implements AuthorizationStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void authenticate(PaymentStrategy paymentStrategy) {
        System.out.print("Masukkan PIN Anda: ");
        String pin = scanner.nextLine();
        
        if (paymentStrategy instanceof DebitCard && !((DebitCard) paymentStrategy).getPin().equals(pin)) {
            throw new AuthorizationException("PIN Debit salah!");
        } else if (paymentStrategy instanceof CreditCard && !((CreditCard) paymentStrategy).getPin().equals(pin)) {
            throw new AuthorizationException("PIN Credit Card salah!");
        } else if (paymentStrategy instanceof EWallet && !((EWallet) paymentStrategy).getPin().equals(pin)) {
            throw new AuthorizationException("PIN E-Wallet salah!");
        }
    }
}
