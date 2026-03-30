package com.ptithcm.demoquanlysinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ptithcm.demoquanlysinhvien.R;
import com.ptithcm.demoquanlysinhvien.helper.DateTimeHelper;
import com.ptithcm.demoquanlysinhvien.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private Context context;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(Context context, List<SinhVien> sinhVienList) {
        this.context = context;
        this.sinhVienList = sinhVienList;
    }


    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.layout_sinhvien_items, null);
        }
        TextView tvID = convertView.findViewById(R.id.tvID);
        TextView tvHoten = convertView.findViewById(R.id.tvHoten);
        TextView tvNgaysinh = convertView.findViewById(R.id.tvNgaysinh);
        SinhVien sinhVien = sinhVienList.get(position);
        tvID.setText(sinhVien.getId().toString());
        tvHoten.setText(sinhVien.getHoten().toString());
        tvNgaysinh.setText(DateTimeHelper.toString(sinhVien.getNgaysinh()));
        return convertView;
    }
}
