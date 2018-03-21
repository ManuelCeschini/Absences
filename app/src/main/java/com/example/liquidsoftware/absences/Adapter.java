package com.example.liquidsoftware.absences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Manuel on 07.03.2018.
 */

public class Adapter extends ArrayAdapter<Database_parameters> {

    Database_parameters params = new Database_parameters();

    private static class ViewHolder {
        public TextView titel;
        public TextView datum_anfang;
        public TextView datum_ende;
        public TextView begruendung;
    }

    public Adapter(Context context, ArrayList<Database_parameters> params){
        super(context, 0, params);
    }

    public View getView(int position, View converView, ViewGroup viewGroup){
        ViewHolder viewHolder;
        if(converView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.item, viewGroup, false);
            viewHolder.titel = (TextView) converView.findViewById(R.id.titel);
            viewHolder.datum_anfang = (TextView) converView.findViewById(R.id.datum_anfang);
            viewHolder.datum_ende = (TextView) converView.findViewById(R.id.datum_ende);
            viewHolder.begruendung = (TextView) converView.findViewById(R.id.datum_ende);
            converView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)converView.getTag();
        }
        viewHolder.titel.setText(params.getTitel());
        viewHolder.datum_anfang.setText(params.getDatumBeginn());
        viewHolder.datum_ende.setText(params.getDatumEnde());
        viewHolder.begruendung.setText(params.getGrund());
        return converView;
    }

}