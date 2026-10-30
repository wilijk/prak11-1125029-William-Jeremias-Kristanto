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

public class a {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Wallet tabungan = new Wallet("tabungan1", 10000);

        while (true) {
            System.out.print("Masukan jumlah yang dibayar : ");
            double dibayar = sc.nextDouble();

            tabungan.pay(dibayar);

        }
    }
}