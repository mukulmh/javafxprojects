package seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String nidno;
    private String sex;

    public Customer(int id, String name, String phone, String address, String nidno, String sex) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.nidno = nidno;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getNidno() {
        return nidno;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", nidno='" + nidno + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}