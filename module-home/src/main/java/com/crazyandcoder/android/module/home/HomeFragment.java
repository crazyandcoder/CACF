package com.crazyandcoder.android.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;
import com.crazyandcoder.android.lib.common.utils.collections.CrazyList;
import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;
import com.crazyandcoder.android.lib.common.utils.thread.CrazyCountDownTimer;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyRecyclerView;
import com.crazyandcoder.android.module.test.ActivityHome;
import com.crazyandcoder.android.module.test.ActivityHomeItem;
import com.crazyandcoder.android.module.test.HomeDataUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterPathConstant.Home.PAGE_HOME)
public class HomeFragment extends Fragment {

    private CrazyRecyclerView mCrazyRecyclerView;
    private HomeAdapter homeAdapter;
    private HomeDataRecyclerViewAdapter homeDataRecyclerViewAdapter;
    private HomeDataAdapter homeDataAdapter;
    private int times = 0;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_blank, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeAdapter = new HomeAdapter(HomeDataUtils.createHomeItemData(), getContext());
        mCrazyRecyclerView = (CrazyRecyclerView) view.findViewById(R.id.recyclerView);
        mCrazyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCrazyRecyclerView.setAdapter(homeAdapter);

        refresh5Shop();

    }

    private void refresh5Shop() {
        //如果接口返回数据为空，则需要隐藏掉此5元入口
        CrazyLog.d("请求接口数据：");
        times = 0;
        List<ActivityHome> data = HomeDataUtils.createActivityHomeData();
        List<List<ActivityHome>> items = CrazyList.splitList(data, 3);
        CrazyCountDownTimer.getInstance().start(true, 3, 0, new CrazyCountDownTimer.ICountDownCallback() {
            @Override
            public void onTick(int time) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (homeAdapter != null) {
                                homeAdapter.updateTime(time);
                            }
                        }
                    });
                }
            }

            @Override
            public void onEndTick() {
                CrazyLog.d("第 " + times + " 组");
                times++;
                if (items != null && !items.isEmpty() && times >= items.size()) {
                    CrazyLog.d(items.size() + "轮分组已完成，重新请求接口：");
                    refresh5Shop();
                } else {
                    List<ActivityHome> list = items.get(times);

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (homeAdapter != null) {
                                    homeAdapter.update5Shop(list);
                                }
                            }
                        });
                    }

                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        CrazyCountDownTimer.getInstance().release();
    }
}
