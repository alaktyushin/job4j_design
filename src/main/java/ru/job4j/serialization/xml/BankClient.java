package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankClient {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    private String[] phones;
    private List<BankAccount> bankAccounts;
    private List<String> addresses;

    public BankClient() {
    }

    public BankClient(String name, int age, String[] phones, List<BankAccount> bankAccounts, List<String> addresses) {
        this.name = name;
        this.age = age;
        this.phones = phones;
        this.bankAccounts = bankAccounts;
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "BankClient:"
                + System.lineSeparator()
                + "   name=" + name
                + System.lineSeparator()
                + "   phones=" + Arrays.toString(phones)
                + System.lineSeparator()
                + "   bankAccounts=" + bankAccounts
                + System.lineSeparator()
                + "   addresses=" + addresses;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        BankAccount account1 = new BankAccount("1000-2000-3000-4000", 'R', true);
        BankAccount account2 = new BankAccount("1000-2000-3000-4001", 'U', false);
        String address1 = "100100, Moscow, Pushkina str, 1, 18";
        String address2 = "200200, Bryansk, Kalinina pr, 2, 28";
        BankClient client = new BankClient("Petr Arsentev", 33, new String[]{"+7000000", "+7000001"},
                List.of(account1, account2), List.of(address1, address2));
        System.out.println(client);
        System.out.println();

        /* Преобразуем объект person в json-строку.
        Gson gson = new GsonBuilder().create();
        String gsonStr = gson.toJson(client);
        System.out.println("Record converted to gson:");
        System.out.println(gsonStr);
        System.out.println();

        /* Преобразуем json-строку в объект.
        BankClient bankClient = gson.fromJson(gsonStr, BankClient.class);
        System.out.println("Record converted from gson:");
        System.out.println(bankClient);
        System.out.println();

        * Преобразуем json-строку в XML-строку.
        JSONObject json = new JSONObject(gsonStr);
        String xmlToJson = XML.toString(json);
        System.out.println("XML-record converted from gson:");
        System.out.println(xmlToJson);
        System.out.println();
         */

        /* Маршалинг в XML и обратно. */
        JAXBContext context = JAXBContext.newInstance(BankClient.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(client, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            BankClient client1 = (BankClient) unmarshaller.unmarshal(reader);
            System.out.println(client1);
        }
    }
}
