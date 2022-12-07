package org.example;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public interface ClockBase {
    void TimeAdd(Hand hand, int value) throws ExceptionIncorrectInput;
    void TimeSet(Hand hand, int value) throws ExceptionIncorrectInput;
    int getCost();
    String getBrand();
    String toString();
    boolean isSeconds();
}
