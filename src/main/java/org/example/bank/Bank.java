package org.example.bank;

import java.util.*;

/*
b. Klasę Bank, reprezentującą bank. Klasa powinna zawierać pole clients,
będące tablicą obiektów typu Client, oraz metody:

* addClient(Client client): dodająca nowego klienta do tablicy clients,
* removeClient(String accountNumber): usuwająca klienta o podanym numerze konta z tablicy clients,
* getClientCount(): zwracająca liczbę klientów w banku,
* getClientByAccountNumber(String accountNumber): zwracająca obiekt klienta o podanym numerze konta,
* deposit(String accountNumber, double amount): dokonująca wpłaty na konto klienta o podanym numerze konta,
* withdraw(String accountNumber, double amount): dokonująca wypłaty z konta klienta o podanym numerze konta,
* transfer(String fromAccountNumber, String toAccountNumber, double amount): dokonująca przelewu
  z jednego konta na drugie.
 */
public class Bank {
    private Set<Client> clients;
    private int clientsNumber;    //stworzona tylko dla ćwiczeń

    public Bank(){
        this.clients = new HashSet<>();
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public void addClientAnotherMethod(Client client){  //zamiast dodawać do Set, zwiększamy liczbę "ręcznie"
        this.clientsNumber += 1;
    }

    public void removeClient(String accountNumber){ // przy List należy się posłużyć nr indeksu (nie znamy go)vdlatego Set
        this.clients.remove(accountNumber);
    }
    public void removeClientAnotherMethod(String accountNumber){
        this.clientsNumber -= 1;
    }

    public int getClientCount(){
       return this.clients.size();
    }
    public int getClientCountAnotherMethod(){
        return this.clientsNumber;            //pole klasy samo sumuje/odejmuje liczbę klientów przy dodawaniu/usuwaniu
    }

    public Client getClientByAccountNumber(String accountNumber){
        for (Client client : clients){
            if (client.getAccountNumber().equals(accountNumber)){
                return client;
            }
        }
        System.out.println("There is no client with specified account number.");
        return null;
    }

    public void deposit(String accountNumber, double amount){
        for(Client client : clients){
            if (!validateAccountNumber(accountNumber)) {
                System.out.println("Account number is invalid. Please correct the number and repeat operation.");
            }
            else if(client.getAccountNumber().equals(accountNumber)){
                client.setBalance(client.getBalance() + amount);
            }
        }
    }

    public void withdraw(String accountNumber, double amount){
        for (Client client : clients){
            if(client.getAccountNumber().equals(accountNumber)){
                if (!validateAccountNumber(accountNumber)){
                    System.out.println("Account number is invalid. Please correct the number and repeat operation.");
                    // w validateAccountNumber method również printuje "..." - czy powinnam tak to zostawić (to nie dubel?)
                }
                else if(client.getBalance() < amount){
                    System.out.println("Funds is deficient to proceed transfer.");
                }
                else client.setBalance(client.getBalance() - amount);
            }
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount){
        boolean clientExist = true;
        for(Client client : clients){
            if (amount > 0.00d){
               if(client.getAccountNumber().equals(fromAccountNumber)){ //czy wystarczy że sprawdzę tylko jeden numer konta?
                   withdraw(fromAccountNumber, amount);
                   deposit(toAccountNumber, amount);
               }
            }
            else clientExist = false;
        }
    }
    public boolean validateAccountNumber(String accountNumber){
        if(accountNumber.replaceAll(" ","").trim().length() != 26){ // trim nie usuwa spacji przed
            System.out.println("Account number is invalid. Please correct the number and repeat operation.");
            return false;
        }
        return true;
    }

    public Set<Client> getClients(){ //brak setterów - został zdeklarowany konstruktor
        return clients;
    }

}
