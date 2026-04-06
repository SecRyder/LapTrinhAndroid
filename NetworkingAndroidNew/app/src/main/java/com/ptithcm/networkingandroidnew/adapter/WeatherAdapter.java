package com.ptithcm.networkingandroidnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptithcm.networkingandroidnew.R;
import com.ptithcm.networkingandroidnew.model.WeatherForecastModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    private Context context;
    private List<WeatherForecastModel> weatherForecastModelList = new ArrayList<>();

    @Override
    public int getCount() {
        return weatherForecastModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public WeatherAdapter(Context context, List<WeatherForecastModel> weatherForecastModelList) {
        this.context = context;
        this.weatherForecastModelList = weatherForecastModelList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_weather_items, null);
        }
        TextView txtTimeForecast = view.findViewById(R.id.txtTimeForecast);
        TextView txtTemperatureForecast = view.findViewById(R.id.txtTemperatureForecast);
        TextView txtWindForecast = view.findViewById(R.id.txtWindForecast);
        ImageView imgForecast = view.findViewById(R.id.imgForecast);
        WeatherForecastModel weatherForecastModel = weatherForecastModelList.get(i);
        txtTimeForecast.setText("" + weatherForecastModel.getTime());
        txtTemperatureForecast.setText(weatherForecastModel.getTemperature() + "°C");
        txtWindForecast.setText(weatherForecastModel.getWindSpeed() + "km");
        Picasso.get().load("https:" + weatherForecastModel.getIcon()).into(imgForecast);
        //imgForecast.src(weatherForecastModel.getTemperature());
        return view;
    }
}
