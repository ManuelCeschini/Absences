package com.example.liquidsoftware.absences;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Manuel on 07.03.2018.
 */

public class Adapter extends ArrayAdapter<Absence> {

    private static class ViewHolder {
        public TextView titel;
        public TextView datum_anfang;
        public TextView datum_ende;
    }

    public Adapter(Context context, ArrayList<Absence> params){
        super(context, 0, params);
    }


    public View getView(int position, View converView, ViewGroup viewGroup){
        Absence params = getItem(position);
        ViewHolder viewHolder;
        if(converView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.item, viewGroup, false);
            viewHolder.titel = converView.findViewById(R.id.titel);
            viewHolder.datum_anfang = converView.findViewById(R.id.datum_anfang);
            viewHolder.datum_ende = converView.findViewById(R.id.datum_ende);
            converView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)converView.getTag();
        }
        viewHolder.titel.setText(params.getTitel());
        viewHolder.datum_anfang.setText("Von: " + params.getDatum_beginn());
        viewHolder.datum_ende.setText("Bis: " + params.getDatum_ende());
        return converView;
    }

}
