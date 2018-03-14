package com.example.liquidsoftware.absences;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Manuel on 07.03.2018.
 */

public class adapter  extends ArrayAdapter<database_parameters> { //extends ArrayAdapter<abfragen>

    database_parameters params = new database_parameters();
    public ListView list;
    public TextView titel;
    public TextView datum_anfang;
    public TextView datum_ende;

    public adapter(Context context, ArrayList<database_parameters> params){
        super(context, 0, params);
        connectors();
        abfragen();
        fuellen();
    }

    //public View getView(int position, View converView, ViewGroup viewGroup{return converView;}

    public void connectors(){
        list.findViewById(R.id.list);
        titel.findViewById(R.id.titel);
        datum_anfang.findViewById(R.id.datum_anfang);
        datum_ende.findViewById(R.id.datum_anfang);
    }
    public void abfragen(){
        //abfragen
    }
    public void fuellen(){

     titel.setText(params.getTitel());
     datum_anfang.setText(params.getDatumBeginn());
     datum_anfang.setText(params.getDatumEnde());
    }

}
