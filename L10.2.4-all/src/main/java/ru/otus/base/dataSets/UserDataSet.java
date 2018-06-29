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

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private List<PhoneDataSet> phones = new ArrayList<>();

    //Important for Hibernate
    public UserDataSet() {
    }

    public UserDataSet(long id, String name, AddressDataSet address, PhoneDataSet... phones) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        List<PhoneDataSet> userPhones = Arrays.asList(phones);
        this.setPhones(userPhones);
    }

    public UserDataSet(String name, AddressDataSet address, PhoneDataSet... phones) {
        this(-1, name, address, phones);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
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
                "id='" + getId() + '\'' +
                "name='" + getName() + '\'' +
                ", address=" + getAddress() +
                ", phones=" + getPhones() +
                '}';
    }
}

