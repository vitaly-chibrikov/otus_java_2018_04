package ru.otus.base.dataSets;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    /**
     * OneToMany unidirectional example
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private List<AddressDataSet> addresses = new ArrayList<>();

    /**
     * OneToMany bidirectional example
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<PhoneDataSet> phones = new ArrayList<>();

    //Important for Hibernate
    public UserDataSet() {
    }

    public UserDataSet(long id, String name, List<AddressDataSet> addresses, PhoneDataSet... phones) {
        this.setId(id);
        this.setName(name);
        this.addresses.addAll(addresses);
        List<PhoneDataSet> userPhones = Arrays.asList(phones);
        this.setPhones(userPhones);
        userPhones.forEach(phone -> phone.setUser(this));
    }

    public UserDataSet(String name, List<AddressDataSet> addresses, PhoneDataSet... phones) {
        this(-1, name, addresses, phones);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AddressDataSet> getAddresses() {
        return addresses;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones.addAll(phones);
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", addresses=" + addresses +
                ", phones=" + phones +
                '}';
    }
}

