package com.ptithcm.n22dcat040_tranthianhnguyet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ptithcm.n22dcat040_tranthianhnguyet.R;
import com.ptithcm.n22dcat040_tranthianhnguyet.adapters.BookGridAdapter;
import com.ptithcm.n22dcat040_tranthianhnguyet.adapters.CartListAdapter;
import com.ptithcm.n22dcat040_tranthianhnguyet.models.Book;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    ArrayList<Book> arrayBook;
    ListView listView;
    CartListAdapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CartFragment() {
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_second, container, false);
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        listView = view.findViewById(R.id.lvBook);

        arrayBook = new ArrayList<>();

        arrayBook.add(new Book("The Great Gatsby", "Fiction", "$12.99", R.drawable.book1));
        arrayBook.add(new Book("1984", "Dystopian", "$11.99", R.drawable.book3));
        arrayBook.add(new Book("The Hobbit", "Fantasy", "$15.99", R.drawable.book5));
        arrayBook.add(new Book("Harry Potter", "Fantasy", "$18.50", R.drawable.book6));
//        adapter = new CartListAdapter(arrayBook,R.layout.dong_sach_frac2,getActivity());
        adapter = new CartListAdapter(arrayBook, R.layout.dong_sach_frac2, requireActivity());
        listView.setAdapter(adapter);
        return view;
    }
}
