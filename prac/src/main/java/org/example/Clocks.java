package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Clocks implements  ClockBase, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    protected int hours;
    protected int minutes;
    protected int cost;
    protected String brand;

    public Clocks(){

    }
    public Clocks( int hours, int minutes, int cost, String brand)  throws ExceptionIncorrectInput{
        if (hours < 0 ) throw new ExceptionIncorrectInput(hours, "Value < 0");
        if (minutes < 0 ) throw new ExceptionIncorrectInput(minutes, "Value < 0");
        if(hours > 23) throw new ExceptionIncorrectInput(hours, "Value > 23");
        else this.hours = hours;
        if (minutes > 59) throw new ExceptionIncorrectInput(minutes, "Value > 59");
        else this.minutes = minutes;
        this.cost = cost;
        this.brand = brand;
    }
    @Override
    public int getCost() {
        return this.cost;
    }
    @Override
    public String getBrand() {
        return this.brand;
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }
    public int getId() {
        return id;
    }
    @Override
    public void TimeAdd(Hand hand, int value) throws ExceptionIncorrectInput{
        if (value < 0) throw new ExceptionIncorrectInput(value, "Value < 0");
        if (hand == Hand.HOURS) this.hours += value;
        if (hand == Hand.MINUTES) this.minutes += value;
        if (hand == Hand.SECONDS) throw new ExceptionIncorrectInput(0, "This clocks hasn't have seconds");
        if (this.minutes >= 60) {
            this.hours = this.minutes / 60;
            this.minutes %= 60;
        }
        if (this.hours >= 24) this.hours %= 24;
    }
    @Override
    public void TimeSet(Hand hand, int value)throws ExceptionIncorrectInput{
        if (value < 0) throw new ExceptionIncorrectInput(value, "Value < 0");
        if (hand == Hand.HOURS) {
            if(value > 23) throw new ExceptionIncorrectInput(value, "Value > 23");
            else this.hours = value;
        }
        if (hand == Hand.MINUTES) {
            if (value > 59) throw new ExceptionIncorrectInput(value, "Value > 59");
            else this.minutes = value;
        }

    }
    public int getSeconds(){
       return -1;
    }
    @Override
    public boolean isSeconds(){
        return false;
    }
    @Override
    public String toString() {
        return "org.example.Clocks{hours=" + this.hours + ", minutes=" + this.minutes + ", cost=" + this.cost + ", brand='" + this.brand + "'}";
    }
}