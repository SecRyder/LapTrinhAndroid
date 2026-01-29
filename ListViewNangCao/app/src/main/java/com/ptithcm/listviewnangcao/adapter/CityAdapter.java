package com.ptithcm.listviewnangcao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptithcm.listviewnangcao.R;
import com.ptithcm.listviewnangcao.model.City;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<City> listCity;

    // Ham khoi tao
    public CityAdapter(Context context, int layout, List<City> listCity) {
        this.context = context;
        this.layout = layout;
        this.listCity = listCity;
    }


    @Override
    public int getCount() {
        return listCity.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvNameCity = convertView.findViewById(R.id.tvNameCity);
        TextView tvLinkHinh = convertView.findViewById(R.id.tvLinkHinh);
        City city = listCity.get(position);
        ivHinh.setImageResource(city.getHinh());
        tvNameCity.setText(city.getNameCity());
        tvLinkHinh.setText(city.getLinkHinh());
        return convertView;
    }
}
