package com.ptithcm.n22dcat040_tranthianhnguyet.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ptithcm.n22dcat040_tranthianhnguyet.fragments.BookGridFragment;
import com.ptithcm.n22dcat040_tranthianhnguyet.fragments.CartFragment;
import com.ptithcm.n22dcat040_tranthianhnguyet.fragments.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BookGridFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
