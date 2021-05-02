package Geekbrains.calculator;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListOperation implements Serializable {

    private static List<Operation> listOperation;

    public static List<Operation> getListOperation() {
        if(listOperation==null){
            listOperation = new ArrayList<>();
        }
        return listOperation;
    }


}
