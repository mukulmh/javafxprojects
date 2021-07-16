package seu;

import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;

public class Reservation {
    private int roomNo;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int custId;

    public Reservation(int roomNo, LocalDate fromDate, LocalDate toDate, int custId) {
        this.roomNo = roomNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.custId = custId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public int getCustId() {
        return custId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "roomNo=" + roomNo +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", custId=" + custId +
                '}';
    }
}