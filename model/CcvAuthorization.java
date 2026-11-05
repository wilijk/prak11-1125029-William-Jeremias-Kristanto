package model;

import java.util.Scanner;

public class CcvAuthorization implements AuthorizationStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void authenticate(PaymentStrategy paymentStrategy) {
        System.out.print("Masukkan 3-digit CCV Anda: ");
        String ccv = scanner.nextLine();
        
        if (paymentStrategy instanceof CreditCard && !((CreditCard) paymentStrategy).getCcv().equals(ccv)) {
            throw new AuthorizationException("CCV salah!");
        }
    }
}
