package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class Doctor {
    private String id;
    private String name;
    private int roomNo;
    private String visitingHour;
    private int fee;
    private String phoneNo;

    public Doctor(String id, String name, int roomNo, String visitingHour, int fee, String phoneNo) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
        this.visitingHour = visitingHour;
        this.fee = fee;
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getVisitingHour() {
        return visitingHour;
    }

    public int getFee() {
        return fee;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomNo=" + roomNo +
                ", visitingHour='" + visitingHour + '\'' +
                ", fee='" + fee + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}