package Geekbrains.calculator;

import java.sql.Struct;

public class Operation {
    private char charSign;
    private String numberOperation;
    private int priority;

    public Operation(char charSign,String numberOperation) {
        this.charSign = charSign;
        this.numberOperation = numberOperation;

        this.priority = 2;
        if (( charSign=='*')||
                ( charSign=='/') ||
                ( charSign=='%')){
            this.priority = 1;
        }
    }

    public Operation() {
        this.charSign = '+';
        this.numberOperation = "";
    }

    public char getCharSign() {
        return charSign;
    }

    public String getNumberOperation() {
        return numberOperation;
    }

    public void setCharSign(char charSign) {
        this.charSign = charSign;
        this.priority = 2;
        if (( charSign=='*')||
                ( charSign=='/') ||
                ( charSign=='%')){
            this.priority = 1;
        }
    }

    public void setNumberOperation(String numberOperation) {
        this.numberOperation = numberOperation;
    }

}
