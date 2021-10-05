package ru.job4j.serialization.json;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankClient {
    private final String name;
    private final int age;
    private final String[] phones;
    private final List<BankAccount> bankAccounts;
    private final List<String> addresses;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String[] getPhones() {
        return phones;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public List<String> getAddresses() {
        return addresses;
    }

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

        final Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", client.getName());
        jsonMap.put("age", String.valueOf(client.getAge()));
        jsonMap.put("phones", Arrays.stream(client.getPhones()).toList());
        jsonMap.put("addresses", client.getAddresses());
        jsonMap.put("bankAccounts", client.getBankAccounts());
        System.out.println(jsonMap);

        final JSONObject jsonObject = new JSONObject(client);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(client));
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
