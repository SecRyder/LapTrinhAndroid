package com.ptithcm.n22dcat040_tranthianhnguyet.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.ptithcm.n22dcat040_tranthianhnguyet.R;
import com.ptithcm.n22dcat040_tranthianhnguyet.adapters.BookGridAdapter;
import com.ptithcm.n22dcat040_tranthianhnguyet.models.Book;

import java.util.ArrayList;

public class BookGridFragment extends Fragment {
    ArrayList<Book> arrayBook;
    GridView gridView;
    BookGridAdapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BookGridFragment() {
    }
    public static BookGridFragment newInstance(String param1, String param2) {
        BookGridFragment fragment = new BookGridFragment();
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
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        gridView = view.findViewById(R.id.gvbook);

        arrayBook = new ArrayList<>();

        arrayBook.add(new Book("The Great Gatsby", "F.Scot Fitzgerald","$12.99", R.drawable.book1));
        arrayBook.add(new Book("To Kill a Mockingbird", "Harper Lee","$14.50", R.drawable.book2));
        arrayBook.add(new Book("1984", "George Orwell","$11.99", R.drawable.book3));
        arrayBook.add(new Book("Pride and Prejudice", "Jane Austen","$9.99", R.drawable.book4));
        arrayBook.add(new Book("The Hobbit", "J.R.R. Tolkien","$15.99", R.drawable.book5));
        arrayBook.add(new Book("Harry Potter", "J.K. Rowling","$18.50", R.drawable.book6));

        adapter = new BookGridAdapter(getActivity(),
                R.layout.dong_sach,
                arrayBook);

        gridView.setAdapter(adapter);

        return view;
    }
}
