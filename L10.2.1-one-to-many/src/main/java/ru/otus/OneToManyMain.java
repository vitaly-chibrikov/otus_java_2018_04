package ru.otus;

import ru.otus.base.DBService;
import ru.otus.base.dataSets.AddressDataSet;
import ru.otus.base.dataSets.PhoneDataSet;
import ru.otus.base.dataSets.UserDataSet;
import ru.otus.dbService.DBServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OneToManyMain {
    public static void main(String[] args) {
        DBService dbService = new DBServiceImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        UserDataSet user1 = new UserDataSet(
                "tully",
                Collections.singletonList(new AddressDataSet("Mira")),
                new PhoneDataSet("+1 234 567 8018"),
                new PhoneDataSet("+7 987 645 4545")
        );

        System.out.println(user1);

        dbService.save(user1);

        List<AddressDataSet> addresses = new ArrayList<>();
        addresses.add(new AddressDataSet("Truda"));
        addresses.add(new AddressDataSet("Moskovskaya"));

        UserDataSet user2 = new UserDataSet(
                "sully",
                addresses,
                new PhoneDataSet("+67 890 344 4422")
        );

        dbService.save(user2);

        System.out.println("____________________________");

        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("sully");
        System.out.println(dataSet);

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }

        dbService.shutdown();
    }
}
