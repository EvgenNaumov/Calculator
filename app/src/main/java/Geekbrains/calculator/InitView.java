package Geekbrains.calculator;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class InitView <T>{
    private T tt;
    private Button button;
    private TextView textView;

    public InitView(T view, final TextView textView) {
//        this.tt = view;
//        if (this.tt instanceof Button){
//            button = (Button)this.tt;
//
//            button.setOnClickListener(new View.OnClickListener(){
//                @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.O)
//                @Override
//                public void onClick(View view) {
//                    MainLogic mainLogic = new MainLogic();
//                    String buttonText = button.getText().toString();
//                    if(mainLogic.canAddtoList(buttonText)){
//                        mainLogic.addOperationToList(buttonText);
//                        mainLogic.textShowOnDisplay(textView);
//                    }
//                }
//            });
//        }
//
    }


}
