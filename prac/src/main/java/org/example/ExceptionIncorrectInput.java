package org.example;

public class ExceptionIncorrectInput extends Exception {
    private int i;
    public ExceptionIncorrectInput(int i, String message){
        super(message);
        this.i = i;
    }
    @Override
    public String toString(){
        return "org.example.ExceptionIncorrectInput{" + "i = " + i + "}:" + this.getMessage();
    }
}
