import java.util.ArrayList;
import java.util.Scanner;

import model.CreditCard;
import model.DebitCard;
import model.EWallet;
import model.PaymentProcessor;
import model.PaymentStrategy;
import model.Wallet;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PaymentProcessor processor = new PaymentProcessor();

        ArrayList<PaymentStrategy> paymentMethods = new ArrayList<>();

        paymentMethods.add(new Wallet("tabungan1", 10000));
        paymentMethods.add(new DebitCard("123456789", 100000, "123456"));
        paymentMethods.add(new CreditCard("987654321", 1000000, "431", "64321"));

        while (true) {
            System.out.println("-- Cafe Menu --");
            System.out.println("1. Bikin Pembayaran");
            System.out.println("2. Tambah Pembayaran");
            System.out.print("pilih: ");
            int pilihMenu = sc.nextInt();

            switch (pilihMenu) {
                case 1:
                    System.out.print("Masukan jumlah yang dibayar: ");
                    double dibayar = sc.nextDouble();

                    ArrayList<PaymentStrategy> methodTersedia = new ArrayList<>();
                    System.out.println("Pilih dari daftar metode pembayaran yang tersedia: ");
                    for (PaymentStrategy strategy : paymentMethods) {
                        if (strategy.canPay(dibayar)) {
                            methodTersedia.add(strategy);

                            String detail = "";
                            if (strategy instanceof Wallet) {
                                detail = "Cash: " + ((Wallet) strategy).getName();
                            } else if (strategy instanceof DebitCard) {
                                detail = "Debit Card: " + ((DebitCard) strategy).getCardNumber();
                            } else if (strategy instanceof CreditCard) {
                                detail = "Credit Card: " + ((CreditCard) strategy).getCardNumber();
                            } else if (strategy instanceof EWallet) {
                                detail = "E-Wallet: " + ((EWallet) strategy).getPhoneNumber();
                            }

                            System.out.println((methodTersedia.size()) + ". " + detail);
                        }
                    }

                    if (methodTersedia.isEmpty()) {
                        System.out.println("gak ada metode pembayaran yang memenuhi batasan transaksi!");
                        break;
                    }
                    System.out.print("Pilih: ");
                    int metodePembayaran = sc.nextInt();

                    if (metodePembayaran >= 1 && metodePembayaran <= methodTersedia.size()) {
                        PaymentStrategy selectedStrategy = methodTersedia.get(metodePembayaran - 1);
                        try {
                            processor.processPayment(selectedStrategy, dibayar);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Tidak ditemukan!");
                    }
                    break;
                case 2:
                    System.out.println("Tambah Pembayaran");
                    System.out.println("1. Wallet");
                    System.out.println("2. Debit Card");
                    System.out.println("3. Credit Card");
                    System.out.println("4. E-Wallet");
                    System.out.print("pilih: ");
                    int pilihTP = sc.nextInt();

                    switch (pilihTP) {
                        case 1:
                            sc.nextLine();
                            System.out.print("Masukan nama wallet: ");
                            String namaWallet = sc.nextLine();

                            System.out.print("Masukan berapa isi walletnya: ");
                            double cashvalue = sc.nextDouble();

                            paymentMethods.add(new Wallet(namaWallet, cashvalue));
                            System.out.println("Berhasil didaftarkan!");
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.print("Masukan card number: ");
                            String noDebit = sc.nextLine();

                            System.out.print("Masukan balance: ");
                            double saldoDebit = sc.nextDouble();

                            System.out.print("Masukan card pin: ");
                            String pinDebit = sc.nextLine();

                            paymentMethods.add(new DebitCard(noDebit, saldoDebit, pinDebit));
                            System.out.println("Berhasil didaftarkan!");
                            break;
                        case 3:
                            sc.nextLine();
                            System.out.print("Masukan card number: ");
                            String noCredit = sc.nextLine();

                            System.out.print("Masukan limit Credit: ");
                            double limitCredit = sc.nextDouble();

                            System.out.print("Masukan cvv: ");
                            String cvv = sc.nextLine();

                            System.out.print("Masukan card pin: ");
                            String pinCredit = sc.nextLine();

                            paymentMethods.add(new CreditCard(noCredit, limitCredit, cvv, pinCredit));
                            System.out.println("Berhasil didaftarkan!");
                            break;

                        case 4:
                            sc.nextLine();
                            System.out.print("Masukan nomor HP E-Wallet: ");
                            String noHp = sc.nextLine();

                            System.out.print("Masukan saldo awal E-Wallet: ");
                            double saldoEW = sc.nextDouble();
                            
                            sc.nextLine();
                            System.out.print("Masukan PIN E-Wallet: ");
                            String pinEW = sc.nextLine();

                            paymentMethods.add(new EWallet(noHp, saldoEW, pinEW));
                            System.out.println("Berhasil didaftarkan!");
                            
                        default:
                            break;
                    }
                    break;

                default:
                    System.out.println("Input yang benar!");
                    break;
            }

        }
    }
}