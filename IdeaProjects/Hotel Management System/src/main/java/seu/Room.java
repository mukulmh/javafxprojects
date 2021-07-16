package seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class Room {
    private int roomNo;
    private String roomType;
    private String bedType;
    private int price;

    public Room(int roomNo, String roomType, String bedType, int price) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.bedType = bedType;
        this.price = price;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getBedType() {
        return bedType;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo=" + roomNo +
                ", rType='" + roomType + '\'' +
                ", bType='" + bedType + '\'' +
                ", price=" + price +
                '}';
    }
}