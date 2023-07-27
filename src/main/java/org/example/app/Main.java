package org.example.app;
/*
c. Klasę Main, w której znajdzie się metoda main, a w niej kod, w którym:
    i. Stworzysz obiekt banku.
    ii. Dodasz kilku klientów do banku, korzystając z metody addClient.
    iii. Wywołasz odpowiednie metody, aby dokonać wpłat, wypłat i przelewów między kontami klientów.
    iv. Wypiszesz na ekranie informacje o wszystkich klientach w banku.

Twoim zadaniem jest implementacja klas Client, Bank oraz metody main zgodnie z podanymi wymaganiami.
 */
import org.example.bank.Bank;
import org.example.bank.Client;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Client client1 = new Client("Ewa", "Zwierzyńska", "11 1234 1234 1234 1234 1234 1234");
        Client client2 = new Client("Jan", "Kowalski", "22 2345 2345 2345 2345 2345 2345");
        Client client3 = new Client("Piotr",  "Zimek", "33 3456 3456 3456 3456 3456 3456");
        Client client4 = new Client("Ziomek",  "Atomek", "44 4567 4567 4567 4567 4567 4567");
        Client client5 = new Client("Inga",  "Pola", "55 5678 5678 5678 5678 5678 5678");
        Client client6 = new Client("Igor", "Niemy", "66 6789 6789 6789 6789 6789 6789");
        Client client7 = new Client("Sulwia", "Plusz", "77 6789 6789 6789 6789 6789 6789");

        Set<Client> list = Set.of(client1, client2, client3, client4, client5,client6, client7);

        Bank centralBank = new Bank();
        for (Client client : list) {           //  jesli odniosę się do centralBank.getClients() to wyjdzie pusta kolekcja?
            centralBank.addClient(client);    //w tym momencie niepotrzebne
        }
        
        debitFundsToAccountsAllClients(centralBank, 5000.00d);
        printNumberOfClients(centralBank.getClients().size());
        printEntireClientsList(centralBank);
        searchClientNameByAccount(centralBank);

        System.out.println("Provide amount to transfer.");
        double amountToTransfer2;
        int counter = list.size();
        //wykonanie przelewów do wszystkich zdeklarowanych klientów (tylko na potrzeby zadania)
        Client[] allTransfers = new Client[list.size()]; // jak stworzyć array przy użyciu centralBank?
        int index = 0;
        for ( Client client : list){
            allTransfers[index] = client;
            index++;
        }
        do{
            Scanner scan2 = new Scanner(System.in);
            amountToTransfer2 = scan2.nextDouble();

            Client clientA = allTransfers[counter -1];
            Client clientB = allTransfers[counter - 2];
            bankTransfer(centralBank,clientA,clientB,amountToTransfer2);
            System.out.println();
            counter--;
            }
        while(counter > 1); // dlaczego program wychodzi z pętli do-while? (zamiast wykonać się 7 razy?
    }
    private static void debitFundsToAccountsAllClients(Bank bank, double amount){
        Client[] debitFunds = new Client[bank.getClients().size()];
        int index = 0;
        for(Client client : bank.getClients()){
            debitFunds[index] = client;
            index++;
            client.setBalance(amount);
        }
    }
    private static void bankTransfer(Bank bank, Client name1, Client name2, double amount){
        bank.transfer(name1.getAccountNumber(), name2.getAccountNumber(), amount);
        System.out.printf("~ withdrawal: %f PLN from %s \nbalance %s: %f \n" +
                "~ debit: %f PLN to %s \nbalance %s: %f",
                -amount, name1, LocalDate.now(), name1.getBalance(),
                amount, name2, LocalDate.now(), name2.getBalance());
    }
    private static void searchClientNameByAccount(Bank bank){
        System.out.println("Search client by account number (26 digits required)");
        Scanner scan = new Scanner(System.in);
        String searchedAccountNumber = scan.nextLine();
        System.out.println(bank.getClientByAccountNumber(searchedAccountNumber) + "\n");
    }

    private static void printEntireClientsList(Bank bank){
        Client[] listOfNames = new Client[bank.getClients().size()];
        int index = 0;
        int position = 1;
        for(Client clientName : bank.getClients()){
            listOfNames[index] = clientName;
            index++;
            System.out.println(position++ + ". " + clientName);
        }
        System.out.println();
    }
    private static void printNumberOfClients(int totalClientsNumber){
        System.out.printf("Client's list, dated %s, contains of %d active clients.",
                LocalDate.now(), totalClientsNumber).println();
        System.out.println();
    }
}

// System.out.println("Provide amount to transfer.");
//         double amountToTransfer2;
//         int clientCounter = 0;
//         do{
//         Scanner scan2 = new Scanner(System.in);
//         amountToTransfer2 = scan2.nextDouble();
//         Client[] allTransfers = new Client[centralBank.getClients().size()];
//         for(int i = 0; i < centralBank.getClients().size() - 1; i++){
//        for(Client client : centralBank.getClients()) {
//        allTransfers[i] = client;
//        Client clientA = allTransfers[i];
//        Client clientB = allTransfers[i+1];
//        bankTransfer(centralBank,clientA,clientB,amountToTransfer2);
////                    bankTransfer(centralBank,allTransfers[clientCounter],allTransfers[++clientCounter],amountToTransfer2);
////                    clientCounter++;
//        }
//        System.out.println();
//        }
//        }
//        while(clientCounter == 6);

//        do{
//                Scanner scan2 = new Scanner(System.in);
//                amountToTransfer2 = scan2.nextDouble();
//                Client[] allTransfers = new Client[list.size()]; // jak stworzyć array przy użyciu centralBank?
//                int index = 0;
//                for ( Client client : list){
//                allTransfers[index] = client;
//                index++;
//                }
//                for(int i = 0; i < allTransfers.length - 1; i++){
//        Client clientA = allTransfers[i];
//        Client clientB = allTransfers[i+1];
//        bankTransfer(centralBank,clientA,clientB,amountToTransfer2);
//        System.out.println();
//        }
//        System.out.println();
//        }
//        while(list.size() == 6);