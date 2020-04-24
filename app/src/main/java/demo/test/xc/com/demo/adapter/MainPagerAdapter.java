package demo.test.xc.com.demo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import demo.test.xc.com.demo.fragment.HomeFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    List<HomeFragment>fragments ;
    List<String>titles;

    public MainPagerAdapter(FragmentManager fm,List fragments,List<String>titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
}
