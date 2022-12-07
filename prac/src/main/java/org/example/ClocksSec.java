package org.example;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class ClocksSec extends Clocks implements ClockBase, Serializable {

    protected int seconds;
    public ClocksSec(){

    }
    public ClocksSec(int hours, int minutes, int seconds, int cost, String brand)  throws ExceptionIncorrectInput{
        super(hours, minutes, cost, brand);
        if (seconds < 0) throw new ExceptionIncorrectInput(seconds, "Value < 0");
        if (seconds > 59) throw new ExceptionIncorrectInput(seconds, "Value > 59");
        else this.seconds = seconds;
    }
    @Override
    public void TimeAdd(Hand hand, int value) throws ExceptionIncorrectInput{
        if (hand == Hand.HOURS || hand == Hand.MINUTES) super.TimeAdd(hand, value);
        else {
            if (value < 0) throw new ExceptionIncorrectInput(value, "Value < 0");
            if (hand == Hand.SECONDS) this.seconds += value;
            if (this.seconds >= 60) {
                this.minutes += this.seconds / 60;
                super.TimeAdd(Hand.MINUTES, 0);
            }
        }
    }
    public void TimeSet(Hand hand, int value)throws ExceptionIncorrectInput{
        if (hand == Hand.HOURS || hand == Hand.MINUTES) super.TimeSet(hand, value);
        else {
            if (value < 0) throw new ExceptionIncorrectInput(value, "Value < 0");
            if (value > 59) throw new ExceptionIncorrectInput(value, "Value > 59");
            else this.seconds = value;

        }

    }
    public int getSeconds() {
        return seconds;
    }
    @Override
    public boolean isSeconds(){
        return true;
    }
    @Override
    public String toString() {
        return "org.example.Clocks{hours=" + this.getHours() + ", minutes=" + this.getMinutes() + ", seconds=" + this.seconds +", cost=" + this.getCost() + ", brand='" + this.getBrand() + "'}";
    }
}
