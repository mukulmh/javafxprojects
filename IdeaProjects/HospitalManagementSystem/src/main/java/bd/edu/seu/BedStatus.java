package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class BedStatus {
    private String bedNo;
    private int roomNo;
    private String status;

    public BedStatus(String bedNo, int roomNo, String status) {
        this.bedNo = bedNo;
        this.roomNo = roomNo;
        this.status = status;
    }

    public String getBedNo() {
        return bedNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "BedStatus{" +
                "bedNo='" + bedNo + '\'' +
                ", roomNo=" + roomNo +
                ", status='" + status + '\'' +
                '}';
    }
}