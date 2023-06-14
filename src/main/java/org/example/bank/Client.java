package org.example.bank;
/*
Napisz program, który będzie symulować działanie banku.
Zaimplementuj klasy:
a. Klasę Client, reprezentującą klienta banku. Klasa powinna zawierać pola:
    * name (typu String),
    * accountNumber (typu String)
    * oraz balance (typu double chyba że dasz radę inaczej)
reprezentujące odpowiednio imię i nazwisko klienta, numer konta oraz saldo.
 */
public class Client {

    // myślę, że wartoby zaimplementować metody equals i hashCode 
    private String name;
    private String surname;
    private String accountNumber;
    private double balance;

    public Client(String name, String surname, String number){
        this.name = name;
        this.surname = surname;
        this.accountNumber = number;
        this.balance = 0;
    }

    @Override  // "nadpisuje" metodtę toString odziedziczoną z klasy Object
    public String toString() {
        return "Client: " +
                "name-" + name +
                ", surname-" + surname +
                ", [account number: " + accountNumber + ']' +
                ", [balance: " + balance +
                ']';
    }

    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public String getNameAndSurname(){
        return this.name + " " + this.surname;
    }
}
