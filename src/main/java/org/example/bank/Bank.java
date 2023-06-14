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

    // Jeśli treść zadania tego nie wymaga, to rozważyłbym użycie Mapy jako kolekcji do przechowywania klientów

    private Set<Client> clients;


    // może być zastąpione uzyciem clients.size() ;)
    private int clientsNumber;    //stworzona tylko dla ćwiczeń

    public Bank(){
        // używanie HashSet dla zbioru obiektów typu Client może być nieco ryzywkowne, jako że Client nie zawiera implementacji metody hashCode()
        this.clients = new HashSet<>();
    }

    public void addClient(Client client){
        // wartoby dodać sprawdzanie, czy klient juz istnieje w tym banku
        this.clients.add(client);
    }

    // Może być usunięte, jako że nie robi tego co obiecuje przez swoją nazwę ;-)
    public void addClientAnotherMethod(Client client){  //zamiast dodawać do Set, zwiększamy liczbę "ręcznie"
        // this.clientsNumber += 1;
    }

    public void removeClient(String accountNumber){ // przy List należy się posłużyć nr indeksu (nie znamy go)vdlatego Set
        this.clients.remove(accountNumber);
    }

    //potrzebne?
    public void removeClientAnotherMethod(String accountNumber){
        this.clientsNumber -= 1;
    }

    public int getClientCount(){
       return this.clients.size();
    }

    /// potrzebne?
    public int getClientCountAnotherMethod(){
        return this.clientsNumber;            //pole klasy samo sumuje/odejmuje liczbę klientów przy dodawaniu/usuwaniu
    }


    // Jest całkiem ok, choć przez wadliwą implementację klasy CLient może nie działać jak nalezy (brak implementacji metod hashCode() i equals() )
    // jako zadanie z * rozważyłbym użycie klasy opakowującej i zwracanie Optional<Client>
    public Client getClientByAccountNumber(String accountNumber){
        for (Client client : clients){
            if (client.getAccountNumber().equals(accountNumber)){
                return client;
            }
        }
        System.out.println("There is no client with specified account number.");
        return null;
    }

    // myślę, że wartoby dodać jakiś typ zwracany (np. boolean) z tej metody, który będzie informował o tym czy opracja się udała
    public void deposit(String accountNumber, double amount){
        for(Client client : clients){
            // Ta validacja mogłaby się chyba odbywać przed ifem, tak aby wykonała się tylko raz :)
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
        // clientExists jest nieużywane ;-)
        boolean clientExist = true;
        for(Client client : clients){
            if (amount > 0.00d){
               if(client.getAccountNumber().equals(fromAccountNumber)){ //czy wystarczy że sprawdzę tylko jeden numer konta?
                   // mysle, że wartoby wyszukac czy istnieje pierwszy oraz drugi klient, a następnie jeśli obaj istnieją to wykonać oczekiwane operacje
                   withdraw(fromAccountNumber, amount);
                   deposit(toAccountNumber, amount);
               }
            }
            else clientExist = false;
        }
    }
    public boolean validateAccountNumber(String accountNumber){
        // Kłamiesz! :-D Usuwa!
        /* sprawdz:
        public static void main(String[] args) {
            System.out.println("{" + "  aa  ".trim() + "}");
        }
         */
        if(accountNumber.replaceAll(" ","").trim().length() != 26){ // trim nie usuwa spacji przed
            System.out.println("Account number is invalid. Please correct the number and repeat operation.");
            return false;
        }
        return true;
    }


    /*
    Ciekawostka: zwracanie kolekcji w taki sposób powoduje, że uzytkownik klasy Bank moze modyfikować kolekcję klientów. Przykładowo:

    bank.getClients().removeAll();

    usunie wszystkich klientów banku.
    Zeby uniknąc takiej sytuacji stosuje się zwracanie kopi kolecji, np. tak:

    return Collections.unmodifiableSet(clients)
     */
    public Set<Client> getClients(){ //brak setterów - został zdeklarowany konstruktor
        return clients;
    }

}
