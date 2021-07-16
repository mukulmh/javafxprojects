package bd.ac.seu;

import java.time.LocalDate;

public class Parts {
    private int id;
    private String name;
    private  String category;
    private String batch;
    private int bin;
    private int quantity;
    private LocalDate date;

    public Parts(int id, String name, String category, String batch, int bin, int quantity,LocalDate date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.batch = batch;
        this.bin = bin;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBatch() {
        return batch;
    }

    public int getBin() {
        return bin;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", batch='" + batch + '\'' +
                ", bin=" + bin +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
