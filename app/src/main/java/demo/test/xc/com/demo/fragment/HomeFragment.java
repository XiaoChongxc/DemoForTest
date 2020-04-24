package demo.test.xc.com.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.Log;

import demo.test.xc.com.demo.R;

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

    boolean  isViewCreate=false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        curName = getArguments().getString("title");
        bgColor = getArguments().getInt("backgroundColor");
        Log.d(TAG, curName + "===>onAttach: ");
    }

    public int getLayoutRes() {
        Log.d(TAG, curName + "==>onCreateView: ");
        return R.layout.fragment_layout_loading;
    }

    protected void initView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, curName + "==>onActivityCreated: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, curName + "==>onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, curName + "==>onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, curName + "==>onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, curName + "==>onDestroy: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, curName + "==>onDestroyView: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, curName + "==>onDetach: ");
    }

    @Override
    protected void fragmentfirstVisible() {
        Log.d(TAG, curName+"fragmentfirstVisible: ===========>第一次可见，应当去更新ui布局");
    }

    protected void startLoadData() {
        Log.d(TAG, curName+"dispatchUserVisible: ===========>当前可见了，应该去更新数据了");
        rootView.setBackgroundColor(bgColor);
    }

    protected void stopLoadData() {
        Log.e(TAG, curName+"dispatchUserVisible: ===========>当前不可见了，应该停止更新了");
    }
}
