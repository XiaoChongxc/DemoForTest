package demo.test.xc.com.demo.adapter;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import demo.test.xc.com.demo.fragment.HomeFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    List<HomeFragment>fragments ;
    List<String>titles;

    public MainPagerAdapter(FragmentManager fm, List fragments, List<String>titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
}
