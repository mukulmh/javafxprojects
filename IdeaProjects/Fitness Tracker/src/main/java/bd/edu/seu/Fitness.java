package bd.edu.seu;

import java.time.LocalDate;

public class Fitness {
    private double weight;
    private int sysBp;
    private int diaBp;
    private LocalDate date;

    public Fitness(LocalDate date, double weight, int sysBp, int diaBp) {
        this.weight = weight;
        this.sysBp = sysBp;
        this.diaBp = diaBp;
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public int getSysBp() {
        return sysBp;
    }

    public int getDiaBp() {
        return diaBp;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FitnessClass{" +
                "weight=" + weight +
                ", sys=" + sysBp +
                ", dia=" + diaBp +
                ", date=" + date +
                '}';
    }
}
