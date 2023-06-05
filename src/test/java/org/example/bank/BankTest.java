package org.example.bank;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class BankTest {
    @Test
    public void shouldReturnClientCount(){
        // given
        Bank bank = new Bank();

        Client client1 = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");
        Client client2 = new Client("Ewa", "Zwierzyńska", "33 1234 1234 1234 1234 1234 1234");

        Set<Client> clients = new HashSet<>();
        clients.add(client1);
        clients.add(client2);
        clients.remove(client2);

        // when
        bank.addClient(client1);
        bank.removeClient("33 1234 1234 1234 1234 1234 1234");

        // then
        assertThat(clients.size()).isEqualTo(1);
    }
    @Test
    void shouldReturnClientCountByAnotherMethod(){
        // given
        Bank bank = new Bank();

        Client client1 = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");
        Client client2 = new Client("Ewa", "Zwierzyńska", "33 1234 1234 1234 1234 1234 1234");

        Set<Client> clients = new HashSet<>();
        clients.add(client1);
        clients.add(client2);
        clients.remove(client2);

        // when
        bank.addClientAnotherMethod(client1);
        bank.addClientAnotherMethod(client2);
        bank.removeClientAnotherMethod(client2.getAccountNumber());

        // then
        assertThat(clients.size()).isEqualTo(1);
    }
    @Test
    public void shouldReturnClientByAccountNumber(){
        // given
        Client client = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");

        Bank bank = new Bank();
        bank.addClient(client);

        // when
        Client testClient = bank.getClientByAccountNumber("11 1234 1234 1234 1234 1234 1234");

        // then
        assertThat(testClient).isEqualTo(client);
    }

    @Test
    void shouldThrowMessageIfAccountNumberIsIncorrect(){ // condition IF
        // given
        Client clientPay = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");
        Bank bank = new Bank();

        // when
        String result = String.valueOf(bank.validateAccountNumber("11 234 1234 1234 1234 1234 1234"));

        // then
        String expectedMessage = "Account number is invalid. Please correct the number and repeat operation.";
        assertNotEquals(result, clientPay.getAccountNumber(), expectedMessage);
    }
    @Test
    void shouldThrowMessageIfClientAccountBalanceIsDeficient(){ // condition ELSE IF
        // given
        Bank bank = new Bank();
        Client clientPay = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");
        clientPay.setBalance(300.00d);

        double actualAccountBalance = clientPay.getBalance();
        double amountTransfer = 500.00d;

        // when
        boolean isBalanceSufficient = actualAccountBalance >= amountTransfer;
        bank.withdraw(clientPay.getAccountNumber(), 500.00d);

        // then
        String expectedMessage = "Funds is deficient to proceed transfer.";
        assertFalse(isBalanceSufficient, expectedMessage);    //dlaczego nie wyrzuciło mi message?
    }
    @Test
    void shouldDepositMoneyIfClientExist(){
        // given
        Client client = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");
        client.setBalance(800.00d);

        Bank bank = new Bank();
        bank.addClient(client);

        // when
        bank.deposit((client.getAccountNumber()), 200.00d);

        // then
        assertThat(client.getBalance()).isEqualTo(1000.00d);
    }
    @Test
    void shouldWithdrawMoneyIfClientExist(){  // condition ELSE
        // given
        Client clientPay = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");
        clientPay.setBalance(4000.00d);

        Client clientReceive = new Client("Ewa", "Zwierzyńska", "33 1234 1234 1234 1234 1234 1234");
        clientReceive.setBalance(5000.00d);

        Bank bank = new Bank();
        bank.addClient(clientPay);
        bank.addClient(clientReceive);

        // when
        bank.getClientByAccountNumber(clientPay.getAccountNumber());
        bank.withdraw((clientPay.getAccountNumber()), 1000.00d);
        bank.deposit(clientReceive.getAccountNumber(), 1000.00d);

        // then
        assertThat(clientPay.getBalance()).isEqualTo(3000.00d);
        assertThat(clientReceive.getBalance()).isEqualTo(6000.00d);
    }
    @Test
    public void shouldReturnTrueIfAccountNumberIsCorrect(){
        // given
        Client client = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");

        // when
        boolean result = client.getAccountNumber().equals("11 1234 1234 1234 1234 1234 1234");

        // then
        assertTrue(result);
    }
    @Test
    public void shouldReturnFalseIfAccountNumberIsIncorrect(){
        // given
        Client client = new Client("Jan", "Kowalski", "11 1234 1234 1234 1234 1234 1234");

        // when
        boolean result = client.getAccountNumber().equals("11 234 1234 1234 1234 1234 1234");

        // then
        String expectedMessage = "Account number is invalid. Please correct the number and repeat operation.";
        assertNotEquals(result, client.getAccountNumber(), expectedMessage);
        // czy powinnam zostawić ten test? przy testach WITHDRAW dla IF mam rozpisany właściwie ten sam test
    }
}