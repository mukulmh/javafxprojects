package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Appointment {
    private String id;
    private String patientName;
    private String docName;
    private int roomNo;
    private LocalDate date;

    public Appointment(String id, String patientName, String docName, int roomNo, LocalDate date) {
        this.id = id;
        this.patientName = patientName;
        this.docName = docName;
        this.roomNo = roomNo;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDocName() {
        return docName;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", docName='" + docName + '\'' +
                ", roomNo=" + roomNo +
                ", date=" + date +
                '}';
    }
}
