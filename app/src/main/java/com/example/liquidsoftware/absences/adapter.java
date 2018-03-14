package com.example.liquidsoftware.absences;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Manuel on 07.03.2018.
 */

public class adapter { //extends ArrayAdapter<abfragen>
    ListView list;
    TextView titel;
    TextView datum;

    public adapter(){
        super();
        connectors();
        abfragen();
        fuellen();
    }

    public void connectors(){
        list.findViewById(R.id.list);
        titel.findViewById(R.id.titel);
        datum.findViewById(R.id.datum);
    }
    public void abfragen(){
        //abfragen
    }
    public void fuellen(){

    }

}
