package Geekbrains.calculator;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ListOperation implements Parcelable {
    private static ListOperation listOperation;
    private List<Operation> list;

    private ListOperation() {
        this.list = new ArrayList<>();
    }


    protected ListOperation(Parcel in) {
    }

    public static final Creator<ListOperation> CREATOR = new Creator<ListOperation>() {
        @Override
        public ListOperation createFromParcel(Parcel in) {
            return new ListOperation(in);
        }

        @Override
        public ListOperation[] newArray(int size) {
            return new ListOperation[size];
        }
    };

    public static ListOperation getInstance() {

        if(listOperation==null){
            listOperation = new ListOperation();
        }
        return listOperation;
    }

    public List<Operation> getList() {
        if (this.list.isEmpty()){
            this.list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
