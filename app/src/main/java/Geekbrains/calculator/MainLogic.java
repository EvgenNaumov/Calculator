package Geekbrains.calculator;

import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;


public class MainLogic {
    private final static String STR_MARK = "+/-";
    private final static char CHAR_MULTIPL = '*';
    private final static char CHAR_ADD = '+';
    private final static char CHAR_DiV = '/';
    private final static char CHAR_REDUCE = '-';
    private final static char CHAR_PERCENT = '%';
    private final static char CHAR_TOTAL = '=';
    private final static char CHAR_DOT = '.';
    private final static char CHAR_COMMA = ',';
    private final static char CHAR_EQUAL = '=';
    private final static char CHAR_DEL = '<';
    private final static char CHAR_CANCEL = 'C';

    private boolean doCalculation;
    private double rez;

    private ListOperation ListOperation = Geekbrains.calculator.ListOperation.getInstance();

    public MainLogic() {
        doCalculation = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void textShowOnDisplay(TextView textView1) {
        String oldText = (String) textView1.getText();

        String text = "";
        int ind = 0;
        for (Operation operation:ListOperation.getList()
             ) {
            String signOp = String.valueOf(operation.getCharSign());
            if (ind==0){
                signOp = "";
            }
            ind++;
            String valeuOp = operation.getNumberOperation();
            try {
                if (!valeuOp.isEmpty()){
                    if (valeuOp.toCharArray()[0]=='-'){
                        valeuOp = "(".concat(valeuOp);
                    }
                }
                String res = signOp + valeuOp;
                text = text.concat(res);
            } catch (NullPointerException e) {
                continue;
            }
        }
        textView1.setText(text);
    }

    private boolean isOperationSign(char c) {
        switch (c) {
            case (CHAR_MULTIPL):
            case (CHAR_DiV):
            case (CHAR_REDUCE):
            case (CHAR_TOTAL):
            case (CHAR_ADD):
                return true;
            default:
                return false;
        }
    }

    private boolean isOperationSignComma(char c) {
        return c == CHAR_COMMA;
    }

    private boolean isOperationSignTotal(char c){
        return c == CHAR_EQUAL;
    }

    private char[] textOnDisplayToArray(String showText){
        char[] arr;
        arr = showText.toCharArray().clone();
        return arr;
    }

    public boolean canAddtoList(String operand, String showText) {

        char inputSymbol = operand.toCharArray()[0];
        //не добавлять знаки если лист пустой (на экране ничего нет)
        if ((ListOperation.getList().isEmpty())
        ) {
            if (isOperationSign(inputSymbol) || isOperationSignTotal(inputSymbol) || inputSymbol==CHAR_PERCENT || operand.equals(STR_MARK)) {
                return false;
            }
            return true;
        }

        //не добавлять знак операции (+,-...) если последний элемент знак а не число или не %
        if (!ListOperation.getList().isEmpty()) {
            char[] arrSymbolOnDisplay = textOnDisplayToArray(showText);
            char lastSymbolOnDisplay = arrSymbolOnDisplay[showText.toCharArray().length-1];

//            char c = ListOperation.getListOperand().get((int) ListOperation.getListOperand().toArray().length - 1).toCharArray()[0];
            if ((isOperationSign(lastSymbolOnDisplay) && isOperationSign(inputSymbol))
            || (lastSymbolOnDisplay==CHAR_PERCENT && inputSymbol==CHAR_PERCENT)) {
                return false;
            }
            if (isOperationSignComma(lastSymbolOnDisplay) && isOperationSignComma(inputSymbol)) {
                return false;
            }

            //если запятая то проверим нет ли уже запятой после последнего знака операции
            if (isOperationSignComma(inputSymbol)) {
                int indexarrSymbol = arrSymbolOnDisplay.length - 1;
                boolean findComma = false;boolean findSign = false;int numSign=0;
                while (indexarrSymbol >= 0) {
//                    if(isOperationSignComma(ListOperation.getListOperand().get(i).toCharArray()[0])){
//                        findComma = true;
//                    }

                    if(isOperationSignComma(arrSymbolOnDisplay[indexarrSymbol])){
                        findComma = true;
                    }
                    if (isOperationSign(arrSymbolOnDisplay[indexarrSymbol])){
                        findSign = true;
                        numSign++;
                        break;
                    }
                    indexarrSymbol--;
                }
                if ((findComma) || (findSign && findComma)){
                    return false;
                }
            }
        }
        return true;
    }

    public void addOperationToList(String operation) {
        if (operation.isEmpty()){
            return;
        }


        Operation lastOperation;
        List<Operation> listOperation = ListOperation.getList();

        //финальное вычисление
//        if (operation.toCharArray()[0]==CHAR_TOTAL) {
//            doCalculation = true;
//            listOperation.clear();
//            return;
//        }
        if(operation.toCharArray()[0]==CHAR_TOTAL){
            return;
        }


        if (listOperation.size() == 0) {
            lastOperation = new Operation();
            listOperation.add(lastOperation);
        } else {
            //если лист не пустой и вводим знак операции
            if (isOperationSign(operation.toCharArray()[0])) {
                lastOperation = new Operation(operation.toCharArray()[0], "");
                lastOperation.setCharSign(operation.toCharArray()[0]);
                listOperation.add(lastOperation);
                return;
            } else {
                lastOperation = listOperation.get((int) listOperation.toArray().length - 1);
            }
        }


        if (operation.toCharArray()[0]==CHAR_CANCEL){
            if (listOperation.isEmpty()) {
                return;
            }
            listOperation.clear();
            return;
        }

        if (operation.toCharArray()[0] == CHAR_DEL) {
            if (listOperation.isEmpty()) {
                return;
            }

            char signOperation = lastOperation.getCharSign();
            String valueOperation = lastOperation.getNumberOperation();
            if (!valueOperation.isEmpty()) {
                valueOperation = valueOperation.substring(0, valueOperation.toCharArray().length - 1);
            }
            if (valueOperation.isEmpty()) {
                listOperation.remove(listOperation.lastIndexOf(lastOperation));
            } else {
                lastOperation.setNumberOperation(valueOperation);
            }
            return;
        }

        if (operation.equals(STR_MARK)) {
            String valueOperation = lastOperation.getNumberOperation();
            if (valueOperation.isEmpty()){
                valueOperation = "-" ;
            }else {
                if (valueOperation.toCharArray()[0]=='-'){
                    valueOperation = valueOperation.substring(1,valueOperation.toCharArray().length-1);
                }else {
                    valueOperation = "-".concat(valueOperation) ;
                }
            }
            lastOperation.setNumberOperation(valueOperation);
            return;
        }

//        if (operation.toCharArray()[0]==(CHAR_PERCENT)){
//            int calcTotal = Integer.parseInt(lastOperation.getNumberOperation())/100;
//            lastOperation.setNumberOperation(String.valueOf(calcTotal));
//            return;
//        }

        if (isOperationSignComma(operation.toCharArray()[0])) {
            String lastOp = lastOperation.getNumberOperation();
            if (lastOp.isEmpty()){
                lastOperation.setNumberOperation("0,");
            }else {
                lastOperation.setNumberOperation(lastOp.concat(operation));
            }
            return;
        }

        lastOperation.setNumberOperation(lastOperation.getNumberOperation().concat(operation));

    }

    public void calculation(TextView textView1, TextView textView2, boolean finalTotal) {
        List<Operation> listOperation = ListOperation.getList();
        if (listOperation.size()==0){
            return;
        }
        double value;
        double valueTotal = 0;
        for (Operation operation:listOperation
             ) {
            char signOperation = operation.getCharSign();
            String valueOperation = operation.getNumberOperation();
            if (signOperation==' '){
                signOperation = CHAR_ADD;
            }
            if (valueOperation.isEmpty()){
                valueOperation = "0";
            }

            //если последний символ = %
            int lastIndex = valueOperation.toCharArray().length-1;
            char[] arr = valueOperation.toCharArray();
            if(arr[lastIndex]==CHAR_PERCENT){
               valueOperation = valueOperation.replace(CHAR_PERCENT,' ');
               value =  Double.parseDouble(valueOperation);
               value = value/100;
            }else{
                value =  Double.parseDouble(valueOperation);
            }

/*
            switch (signOperation){
                case CHAR_ADD:valueTotal += value;
                case CHAR_REDUCE: valueTotal -= value;
                case CHAR_MULTIPL: valueTotal *= value;
                case CHAR_DiV: valueTotal /= value;
            }
*/
            if (signOperation==CHAR_ADD){
                valueTotal += value;
            }
            if (signOperation==CHAR_REDUCE){
                valueTotal -= value;
            }
            if (signOperation==CHAR_MULTIPL){
                valueTotal = valueTotal * value;
            }
            if (signOperation==CHAR_DiV){
                valueTotal = valueTotal / value;
            }

        }
        textView2.setText(String.valueOf(valueTotal));
        rez = valueTotal;

        if (finalTotal){
            textView1.setText(String.valueOf(valueTotal));
            textView2.setText("");
            listOperation.clear();

        }


    }

}
