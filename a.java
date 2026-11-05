import java.util.ArrayList;
import java.util.Scanner;

class Wallet {
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

    boolean validate(double amount) {
        return cashValue >= amount;
    }

    void pay(double amount) {
        if (validate(amount)) {
            cashValue -= amount;
            System.out.println("Pembayaran cash berhasil, sisa wallet: " + cashValue);
        } else {
            System.out.println("Saldo tidak mencukupi!");
            System.out.println("Sisa saldo: " + cashValue);
        }

    }

}

class DebitCard {
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

    boolean validate(double amount) {
        return balance >= amount;
    }

    void pay(double amount) {
        if (validate(amount)) {
            balance -= amount;
            System.out.println("Pembayaran Debit berhasil, sisa Debit Card: " + balance);
        } else {
            System.out.println("Saldo tidak mencukupi!");
            System.out.println("Sisa saldo Debit Card: " + balance);
        }

    }
}

class CreditCard {
    String cardNumber;
    double limit;
    double currentDebt;
    String cvv;
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

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public CreditCard(String cardNumber, double limit, String cvv, String pin) {
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.currentDebt = 0;
        this.cvv = cvv;
        this.pin = pin;
    }

    boolean validate(double amount) {
        return currentDebt + amount <= limit;
    }

    void pay(double amount) {
        if (validate(amount)) {
            currentDebt += amount;
            limit -= amount;
            System.out.println("Pembayaran Credit berhasil, sisa limit Credit Card: " + limit);
        } else {
            System.out.println("Pembayaran gagal!");
            System.out.println("Sisa limit Credit Card: " + limit);
        }

    }
}

public class a {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Wallet> lWallets = new ArrayList<>();
        ArrayList<DebitCard> lDebitCards = new ArrayList<>();
        ArrayList<CreditCard> lCreditCards = new ArrayList<>();

        Wallet tabungan = new Wallet("tabungan1", 10000);
        DebitCard debitCard = new DebitCard("123456789", 100000, "123456");
        CreditCard creditCard = new CreditCard("987654321", 1000000, "431", "64321");

        lWallets.add(tabungan);
        lDebitCards.add(debitCard);
        lCreditCards.add(creditCard);

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
                    System.out.println("Pilih dari daftar metode pembayaran yang tersedia: ");
                    int count = 0;
                    for (int i = 0; i < lWallets.size(); i++) {
                        System.out.println((count + 1) + ". Cash: " + lWallets.get(i).getName());
                        count++;
                    }
                    for (int i = 0; i < lDebitCards.size(); i++) {
                        System.out.println((count + 1) + ". Debit Card: " + lDebitCards.get(i).getCardNumber());
                        count++;
                    }
                    for (int i = 0; i < lCreditCards.size(); i++) {
                        System.out.println((count + 1) + ". Credit Card: " + lCreditCards.get(i).getCardNumber());
                        count++;
                    }
                    System.out.print("Pilih: ");
                    int metodePembayaran = sc.nextInt();

                    int countp = 1;
                    if (metodePembayaran <= count && metodePembayaran >= 1) {

                        for (Wallet lw : lWallets) {
                            if (countp == metodePembayaran) {
                                lw.pay(dibayar);
                            }
                            countp++;
                        }
                        for (DebitCard dc : lDebitCards) {
                            if (countp == metodePembayaran) {
                                dc.pay(dibayar);
                            }
                            countp++;
                        }
                        for (CreditCard cc : lCreditCards) {
                            if (countp == metodePembayaran) {
                                cc.pay(dibayar);
                            }
                            countp++;
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
                    int pilihTP = sc.nextInt();

                    switch (pilihTP) {
                        case 1:
                            sc.nextLine();
                            System.out.print("Masukan nama wallet: ");
                            String namaWallet = sc.nextLine();

                            System.out.print("Masukan berapa isi walletnya: ");
                            double cashvalue = sc.nextDouble();

                            lWallets.add(new Wallet(namaWallet, cashvalue));
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

                            lDebitCards.add(new DebitCard(noDebit, saldoDebit, pinDebit));
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

                            lCreditCards.add(new CreditCard(noCredit, limitCredit, cvv, pinCredit));
                            System.out.println("Berhasil didaftarkan!");
                            break;

                        default:
                            System.out.println("Tidak ditemukan!");
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