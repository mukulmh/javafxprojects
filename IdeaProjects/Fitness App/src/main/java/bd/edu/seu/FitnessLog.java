package bd.edu.seu;

import java.time.LocalDate;

public class FitnessLog {
    private double weight;
    private int systolic;
    private int diastolic;
    private String time;
    private LocalDate date;

    public FitnessLog(LocalDate date, String time, double weight, int systolic, int diastolic) {
        this.weight = weight;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.time = time;
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public int getSystolic() {
        return systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public String getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FitnessLog{" +
                "weight=" + weight +
                ", systolic=" + systolic +
                ", diastolic=" + diastolic +
                ", time='" + time + '\'' +
                ", date=" + date +
                '}';
    }
}
