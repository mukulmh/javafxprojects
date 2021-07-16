package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class Room {

    private int roomNo;
    private String description;
    private String roomType;

    public Room(int roomNo, String description, String roomType) {
        this.roomNo = roomNo;
        this.description = description;
        this.roomType = roomType;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getDescription() {
        return description;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo=" + roomNo +
                ", description='" + description + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}