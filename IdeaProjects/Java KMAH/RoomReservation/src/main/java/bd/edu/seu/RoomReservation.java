package bd.edu.seu;

import java.time.LocalDate;

public class RoomReservation {
    public String name;
    public int room;
    public LocalDate from;
    public LocalDate to;

    public RoomReservation(String name, int room, LocalDate from, LocalDate to) {
        this.name = name;
        this.room = room;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public int getRoom() {
        return room;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "name='" + name + '\'' +
                ", room=" + room +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
