package demo.test.xc.com.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewStub;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.test.xc.com.demo.R;
import demo.test.xc.com.demo.adapter.HomeListFragment;

public class HomeFragment extends LazyFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment getInstance(String title, @ColorRes int backgroundColor) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("backgroundColor", backgroundColor);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    String curName = "";
    int bgColor = 0;


    ViewStub viewStub;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        curName = getArguments().getString("title");
        bgColor = getArguments().getInt("backgroundColor");
    }

    public int getLayoutRes() {
        Log.d(TAG, curName + "==>onCreateView: ");
        return R.layout.fragment_layout_loading;
    }

    protected void initView() {

    }


    RecyclerView recyclerView;
    HomeListFragment adapter ;


    @Override
    protected void fragmentfirstVisible() {
        if(curName.equals("home1")) {
            Log.d(TAG, curName + "fragmentfirstVisible: ===========>第一次可见，应当去更新ui布局");
            viewStub = rootView.findViewById(R.id.main_view_stub);
            recyclerView = (RecyclerView) viewStub.inflate();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            List<String> titles = new ArrayList<>();
            titles.add("指南针");
            adapter =new HomeListFragment(titles);
            recyclerView.setAdapter(adapter);
        }
    }

    protected void startLoadData() {


    }

    protected void stopLoadData() {
        Log.e(TAG, curName+"dispatchUserVisible: ===========>当前不可见了，应该停止更新了");
    }
}
