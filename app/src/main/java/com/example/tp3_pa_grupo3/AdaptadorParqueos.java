package com.example.tp3_pa_grupo3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import OpenHelper.SQLite_OpenHelper;

public class AdaptadorParqueos extends BaseAdapter {
    private Context context;
    private List<EParqueos> par ;
    private SQLite_OpenHelper sql;

    public AdaptadorParqueos(Context context) {
        this.context = context;
        sql = new SQLite_OpenHelper(context);
        try {

            par = sql.selectAllParqueo();
        } catch (Exception ex) {
            par= null;

        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        try {

            par = sql.selectAllParqueo();
        } catch (Exception ex) {
            par= null;

        }
    }

    @Override
    public int getCount() {

        return par!=null? par.size() : 0;
    }

    @Override
    public EParqueos getItem(int position) {
        return par.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getIds();

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view== null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.grid_item, viewGroup, false);

        }

        TextView Matricula = ((TextView) view.findViewById(R.id.t_matricula));
        TextView Tiempo = ((TextView) view.findViewById(R.id.t_tiempo));

        if( par != null){
            final EParqueos item = (EParqueos) getItem(position);

            Matricula.setText(item.getMatricula());
            Tiempo.setText(item.getTiempo());
        }

        return view;


    }
}

