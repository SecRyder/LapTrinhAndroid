package com.ptithcm.n22dcat040_tranthianhnguyet.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptithcm.n22dcat040_tranthianhnguyet.R;
import com.ptithcm.n22dcat040_tranthianhnguyet.models.Book;

import java.util.List;

public class CartListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Book> bookList;

    public CartListAdapter(List<Book> bookList, int layout, Context context) {
        this.bookList = bookList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(layout, null);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            convertView = inflater.inflate(layout, viewGroup, false);
        }
        //anh xạ
        TextView txtTenSach = convertView.findViewById(R.id.txtTenSach);
        TextView txtTenTacGia = convertView.findViewById(R.id.txtTenTacGia);
        TextView txtGia = convertView.findViewById(R.id.txtGia);
        ImageView imgHinh = convertView.findViewById(R.id.imgHinh);

        Book book = bookList.get(position);
        txtTenSach.setText(book.getTenSach());
        txtTenTacGia.setText(book.getTenTacGia());
        txtGia.setText(book.getGia());

        imgHinh.setImageResource(book.getHinh());
        return convertView;
    }
}
