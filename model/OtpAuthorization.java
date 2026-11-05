package model;

import java.util.Scanner;

public class OtpAuthorization implements AuthorizationStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void authenticate(PaymentStrategy paymentStrategy) {
        System.out.print("Masukkan kode OTP Anda (Simulasi: ketik apa saja): ");
        String otp = scanner.nextLine();
        
        if (otp.trim().isEmpty()) {
            throw new AuthorizationException("OTP tidak boleh kosong!");
        }
    }
}
