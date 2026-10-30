import java.util.Scanner;

class Wallet{
    private String name;
    private double cashValue;
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getCashValue(){
        return cashValue;
    }

    public void setCashValue(double cashValue){
        this.cashValue = cashValue;
    }

    public Wallet(String name, double cashValue){
        this.name = name;
        this.cashValue = cashValue;
    }

    boolean validate(double amount){
        if (cashValue >= amount) {
            return true;
        } return false;
    }

    void pay (double amount){
        if (validate(amount)){
            cashValue -= amount;
            System.out.println("Pembayaran cash berhasil, sisa wallet: " + cashValue);
        } else {
            System.out.println("Saldo tidak mencukupi!");
            System.out.println("Sisa saldo: " +  cashValue);
        }

    }

}

class DebitCard{
    private String cardNumber;
    private double balance;
    private String pin;

    public String getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public String getPin(){
        return pin;
    }

    public void setPin(String pin){
        this.pin = pin;
    }

    public DebitCard(String cardNumber, double balance, String pin){
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
    }

    boolean validate(double amount){
        if (balance >= amount) {
            return true;
        } return false;
    }

    void pay (double amount){
        if (validate(amount)){
            balance -= amount;
            System.out.println("Pembayaran Debit berhasil, sisa Debit Card: " + balance);
        } else {
            System.out.println("Saldo tidak mencukupi!");
            System.out.println("Sisa saldo Debit Card: " +  balance);
        }

    }

}
public class a {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Wallet tabungan = new Wallet("tabungan1", 10000);
        DebitCard debitCard = new DebitCard("123456789", 100000, "123456");

        while (true) {
            System.out.print("Masukan jumlah yang dibayar: ");
            double dibayar = sc.nextDouble();  
            System.out.println("-- Metode Pembayaran --");
            System.out.println("1. Cash");
            System.out.println("2. Debit");
            System.out.print("Pilih: ");
            int metodePembayaran = sc.nextInt();
            
            if (metodePembayaran == 1) {
                tabungan.pay(dibayar);
            } else {
                debitCard.pay(dibayar);
            }
        
        }
    }
}