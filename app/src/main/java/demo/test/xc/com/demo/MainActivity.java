package demo.test.xc.com.demo;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import demo.test.xc.com.demo.adapter.MainPagerAdapter;
import demo.test.xc.com.demo.fragment.Home5Fragment;
import demo.test.xc.com.demo.fragment.HomeFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout =findViewById(R.id.tablayout);
        ViewPager viewPager=findViewById(R.id.main_pager);
        List<Fragment> fragments=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        titles.add("home1");
        titles.add("home2");
        titles.add("home3");
        titles.add("home4");
        titles.add("home5");
        fragments.add(HomeFragment.getInstance("home1",R.color.title_color));
        fragments.add(HomeFragment.getInstance("home2",R.color.color_FFD829));
        fragments.add(HomeFragment.getInstance("home3",R.color.seekbarPrimary));
        fragments.add(HomeFragment.getInstance("home4",R.color.pickerview_timebtn_pre));
        fragments.add(Home5Fragment.getInstance("home5",R.color.white_60));
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),fragments,titles));
        tabLayout.setupWithViewPager(viewPager);


    }
}
