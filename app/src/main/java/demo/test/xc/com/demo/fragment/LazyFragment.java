package demo.test.xc.com.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * 懒加载的fragment
 *
 * 实现思路：
 *  从源码我们可以得知， viewpager + fragment会默认缓存当前页面，和下一个页面
 *  通过setUserVisibleHint(boolean)控制每个页面的显示，然后才开始走fragment的生命周期，需要在view创建之后才能开始监控
 *  所以，我们可以通过监控这个方法，来达到懒加载的目的
 *  第一次进入的时候， 触发setUserVisibleHint()方法时，布局没有创建， 所以我们要手动调用一次setUserVisibleHint()
 *  为了更好的节省性能和展示数据， 一般都是预加载一个空的页面，然后在fragment第一次可见的时候去加载需要展示的布局 (viewStub)
 *  然后我们需要严格控制在  只有是  可见====》不可见的时候 ， 去停止加载数据， 在 不可见====》可见的时候， 去加载数据 ，避免多次触发
 * @see #fragmentfirstVisible()  第一次fragment可见时触发
 * @see #dispatchUserVisible(boolean)   fragment 可见状态发生变化的时候触发
 * 会先执行  fragmentfirstVisible  在执行initView  ，
 */
public abstract class LazyFragment extends Fragment {
    boolean isViewCreate = false;
    boolean curVisibleState = false;
    boolean isFirstVisible=true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreate){
            if(isVisibleToUser &&  isFirstVisible){
                fragmentfirstVisible();
                isFirstVisible=false;
            }
            if (!curVisibleState && isVisibleToUser) {
                //从不可见，变为可见  需要更新数据
                dispatchUserVisible(true);
            } else if (curVisibleState && !isVisibleToUser) {
                //从可见，变成不可见 需要停止更新数据
                dispatchUserVisible(false);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreate = true;
        if (rootView == null)
            rootView = inflater.inflate(getLayoutRes(), container, false);
        if (getUserVisibleHint())
            setUserVisibleHint(true);

        initView();
        return rootView;
    }

    protected abstract void initView();

    public abstract @LayoutRes    int getLayoutRes();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint())
            dispatchUserVisible(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint())
            dispatchUserVisible(false);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreate = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * fragment 第一次可见， 用来加载布局文件，
     * 一般来说， 先预加载一个空的布局， 让页面缓存较少数据， 在fragment第一次显示之后就去加载新的布局
     */
    protected  void fragmentfirstVisible(){


    }
    /**
     * 分发 用户可见事件
     *
     * @param isVisible
     */
    protected void dispatchUserVisible(boolean isVisible) {
        //这里， 我们应该是只在变化的时候更新， 所以跟上次情况一样我们就不跟新了
        if (curVisibleState == isVisible) return;

        curVisibleState = isVisible;
        if (isVisible) {
            startLoadData();
        } else {
            stopLoadData();
        }
    }

    protected void startLoadData() {
    }

    protected void stopLoadData() {
    }

}
