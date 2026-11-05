package model;

import java.util.List;
import java.util.Scanner;

public class PaymentProcessor {
    private Scanner scanner = new Scanner(System.in);

    public void processPayment(PaymentStrategy paymentStrategy, double amount) {
        if (!paymentStrategy.validate(amount)) {
            throw new IllegalArgumentException("Transaksi Gagal: Saldo atau limit tidak mencukupi!");
        }

        List<AuthorizationMethod> requiredAuthorizations = paymentStrategy.getRequiredAuthorizations();
        
        for (int i = 0; i < requiredAuthorizations.size(); i++) {
            AuthorizationMethod method = requiredAuthorizations.get(i);

            switch (method) {
                case PIN:
                    System.out.print("Masukkan PIN Anda: ");
                    String pin = scanner.nextLine();
                    
                    if (paymentStrategy instanceof DebitCard && !((DebitCard) paymentStrategy).getPin().equals(pin)) {
                        throw new AuthorizationException("PIN Debit salah!");
                    } else if (paymentStrategy instanceof CreditCard && !((CreditCard) paymentStrategy).getPin().equals(pin)) {
                        throw new AuthorizationException("PIN Credit Card salah!");
                    }
                    break;
                    
                case CCV:
                    System.out.print("Masukkan 3-digit CCV Anda: ");
                    String ccv = scanner.nextLine();
                    
                    if (paymentStrategy instanceof CreditCard && !((CreditCard) paymentStrategy).getCcv().equals(ccv)) {
                        throw new AuthorizationException("CCV salah!");
                    }
                    break;

                case OTP:
                    System.out.print("Masukkan OTP Anda: ");
                    String otp = scanner.nextLine();
                    if (otp.trim().isEmpty()) {
                        throw new AuthorizationException("OTP tidak boleh kosong!");
                    }
                    break;
                    
                default:
                    break;
            }
        }

        paymentStrategy.pay(amount);
    }
}

