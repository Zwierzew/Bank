package org.example.bank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class BetterBank {

    private Map<String, Client> clients;

    public BetterBank() {
        this.clients = new HashMap<>();
    }

    public boolean addClient(Client client) {
        String accountNumber = client.getAccountNumber();
        if (clients.containsKey(accountNumber)) {
            System.out.println("Client already defined in the bank.");
            return false;
        } else {
            this.clients.put(accountNumber, client);
            return true;
        }
    }

    public boolean removeClient(String accountNumber) {
        return this.clients.remove(accountNumber) != null; // remove() zwróci null gdy client nie istnieje w bazie
    }

    public int getClientCount() {
        return this.clients.size();
    }

    public Client getClientByAccountNumber(String accountNumber) {
        Client client = clients.get(accountNumber);

        if (client == null)
            System.out.println("There is no client with specified account number.");
        return client;
    }

    // myślę, że wartoby dodać jakiś typ zwracany (np. boolean) z tej metody, który będzie informował o tym czy opracja się udała
    public boolean deposit(String accountNumber, double amount) {
        if (!validateAccountNumber(accountNumber)) {
            System.out.println("Account number is invalid. Please correct the number and repeat operation.");
            return false;
        }

        Client client = clients.get(accountNumber);
        if (client != null) {
            client.setBalance(client.getBalance() + amount);
            return true;
        } else {
            System.out.println("Cannot find client with provided account number");
            return false;
        }
    }

    public boolean withdraw(String accountNumber, double amount) {
        if (!validateAccountNumber(accountNumber)) {
            System.out.println("Account number is invalid. Please correct the number and repeat operation.");
            return false;
        }

        Client client = clients.get(accountNumber);
        if (client != null) {
            client.setBalance(client.getBalance() - amount);
            return true;
        } else {
            System.out.println("Cannot find client with provided account number");
            return false;
        }
    }


    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Client fromClient = clients.get(fromAccountNumber);
        Client toClient = clients.get(toAccountNumber);

        if (fromClient == null || toClient == null) {
            System.out.println("Cannot find clients with provided numbers");
            return false;
        } else {
            // tu trochę uprosciłem, bo wewnątrz poniższych metod jest dodatkowa logika do validacji i mogłoby się wydarzyć, że od jednego klienta pobierzemy pieniądze, a drugiemu ich nie dodamy
            withdraw(fromAccountNumber, amount);
            deposit(toAccountNumber, amount);
            return true;
        }
    }
    public boolean validateAccountNumber(String accountNumber) {
        if (accountNumber.replaceAll(" ", "").trim().length() != 26) {
            System.out.println("Account number is invalid. Please correct the number and repeat operation.");
            return false;
        }
        return true;
    }


    public Set<Client> getClients() {
        return new HashSet<>(clients.values());
    }

}
