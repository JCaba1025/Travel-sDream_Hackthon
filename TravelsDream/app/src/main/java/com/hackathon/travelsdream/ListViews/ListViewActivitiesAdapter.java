package com.hackathon.travelsdream.ListViews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hackathon.travelsdream.R;
import com.hackathon.travelsdream.model.Actividad;

import java.util.ArrayList;

public class ListViewActivitiesAdapter extends ArrayAdapter<Actividad> {

    ArrayList<Actividad> Act;
    Context context;


    public ListViewActivitiesAdapter(Context context, ArrayList<Actividad> items) {
        super(context, R.layout.activities_list_row, items);

        this.context = context;
        Act = items;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        if (convertView == null){

            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.activities_list_row, null);

        }

        //CircleImageView ActView = convertView.findViewById(R.id.Iv_Act);
        TextView ActName = convertView.findViewById(R.id.Tv_NameAct);
        TextView ActDepart = convertView.findViewById(R.id.Tv_Depart);
        TextView ActMunic = convertView.findViewById(R.id.Tv_Municipio);
        TextView ActDescrip = convertView.findViewById(R.id.Tv_Desc);



        //ActView.setImageResource(1);
        String nameAc = Act.get(position).getName();
        ActName.setText(nameAc);
        ActDepart.setText(Act.get(position).getDepartamento());
        ActMunic.setText(Act.get(position).getMunicipio());

        String DescripDetall = "Descripción: Está actividad se desarrolla en: "+Act.get(position).getPlace()+ "  A un precio de: " +Act.get(position).getPrice()+ " Y consta de:  "+ Act.get(position).getDescrip()+ "";
        ActDescrip.setText(DescripDetall);


        return convertView;

    }


}
