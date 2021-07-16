package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;

public class Patient {
    private String id;
    private String name;
    private String phoneNo;
    private String address;
    private String assignedTo;
    private LocalDate date;

    public Patient(String id, String name, String phoneNo, String address, String assignedTo, LocalDate date) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.assignedTo = assignedTo;
        this.date = date;
    }

    public String  getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone_no='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", assignedTo=" + assignedTo +
                ", date=" + date +
                '}';
    }
}