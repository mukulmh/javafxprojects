package bd.edu.seu;

public class BankAccount {
    private int number;
    private String name;
    private String address;
    private double balance;

    public BankAccount(int number, String name, String address, double balance) {
        this.number = number;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public BankAccount(){
        
    }


    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}

