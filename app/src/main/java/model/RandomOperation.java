package model;

import java.util.Random;

public class RandomOperation {
    private int number1;
    private int number2;
    private String operator;
    private String[] operators = {"+","-","*","/"};
    Random random = new Random();


    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return number1 +"     " + operator + "     " + number2 + "     =     ?";
    }

    public void launchOperation(){
        setNumber1(random.nextInt(10));
        setNumber2(random.nextInt(10));
        setOperator(operators[random.nextInt(operators.length)]);

        while (number2 == 0 && operator.equals("/")){   //Handling division by 0
            setNumber2(random.nextInt(10));
        }


    }
}
