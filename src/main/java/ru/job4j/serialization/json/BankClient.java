package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class BankClient {
    private final String name;
    private final int age;
    private final String[] phones;
    private final List<BankAccount> bankAccounts;
    private final List<String> addresses;

    public BankClient(String name, int age, String[] phones, List<BankAccount> bankAccounts, List<String> addresses) {
        this.name = name;
        this.age = age;
        this.phones = phones;
        this.bankAccounts = bankAccounts;
        this.addresses = addresses;
    }

    public static void main(String[] args) {
        final BankAccount account1 = new BankAccount("1000-2000-3000-4000", 'R', true);
        final BankAccount account2 = new BankAccount("1000-2000-3000-4001", 'U', false);
        final String address1 = "100100, Moscow, Pushkina str, 1, 18";
        final String address2 = "200200, Bryansk, Kalinina pr, 2, 28";
        final BankClient client = new BankClient("Petr Arsentev", 33, new String[]{"+7000000", "+7000001"},
                List.of(account1, account2), List.of(address1, address2));
        System.out.println(client);
        System.out.println();

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        final String gsonStr = gson.toJson(client);
        System.out.println("Record converted to gson:");
        System.out.println(gsonStr);
        System.out.println();

        /* Преобразуем json-строку в объект. */
        final BankClient bankClient = gson.fromJson(gsonStr, BankClient.class);
        System.out.println("Record converted from gson:");
        System.out.println(bankClient);
        System.out.println();
    }

    @Override
    public String toString() {
        return "BankClient{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", phones=" + Arrays.toString(phones)
                + ", bankAccounts=" + bankAccounts
                + ", addresses=" + addresses
                + '}';
    }
}
