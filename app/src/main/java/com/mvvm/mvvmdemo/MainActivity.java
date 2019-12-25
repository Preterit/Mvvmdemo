package com.mvvm.mvvmdemo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.mvvm.base.activity.BaseActivity;
import com.mvvm.base.viewmodel.IBaseViewModel;
import com.mvvm.mvvmdemo.databinding.ActivityMainBinding;
import com.mvvm.mvvmdemo.otherfragments.AccountFragment;
import com.mvvm.mvvmdemo.otherfragments.CategoryFragment;
import com.mvvm.mvvmdemo.otherfragments.ServiceFragment;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity<ActivityMainBinding, IBaseViewModel> {

    private Fragment mHomeFragment;
    private CategoryFragment mCategoryFragment = new CategoryFragment();
    private ServiceFragment mServiceFragment = new ServiceFragment();
    private AccountFragment mAccountFragment = new AccountFragment();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.menu_home));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            disableShiftMode(mBinding.bottomView);
        }
        showBadgeView(2, 2);


        CCResult result = CC.obtainBuilder("News").setActionName("getHomeFragment").build().call();
        mHomeFragment = (Fragment) result.getDataMap().get("fragment");
        fromFragment = mHomeFragment;


        mBinding.bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragCategory = null;
                // init corresponding fragment
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        fragCategory = mHomeFragment;
                        break;
                    case R.id.menu_categories:
                        fragCategory = mCategoryFragment;
                        break;
                    case R.id.menu_services:
                        fragCategory = mServiceFragment;
                        break;
                    case R.id.menu_account:
                        fragCategory = mAccountFragment;
                        break;
                }
                //Set bottom menu selected item text in toolbar
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(item.getTitle());
                }
                switchFragment(fromFragment, fragCategory);
                fromFragment = fragCategory;
                return true;
            }
        });
        mBinding.bottomView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(mBinding.container.getId(), mHomeFragment);
        transaction.commit();
    }

    Fragment fromFragment;

    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            FragmentManager manger = getSupportFragmentManager();
            FragmentTransaction transaction = manger.beginTransaction();
            if (!to.isAdded()) {
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.add(R.id.container, to).commit();
                }

            } else {
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.show(to).commit();
                }

            }
        }
    }

    @Override
    protected void onRetryBtnClick() {

    }


    @SuppressLint({"NewApi", "RestrictedApi"})
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
    }

    /**
     * BottomNavigationView显示角标
     *
     * @param viewIndex  tab索引
     * @param showNumber 显示的数字，小于等于0是将不显示
     */
    private void showBadgeView(int viewIndex, int showNumber) {
        // 具体child的查找和view的嵌套结构请在源码中查看
        // 从bottomNavigationView中获得BottomNavigationMenuView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mBinding.bottomView.getChildAt(0);
        // 从BottomNavigationMenuView中获得childview, BottomNavigationItemView
        if (viewIndex < menuView.getChildCount()) {
            // 获得viewIndex对应子tab
            View view = menuView.getChildAt(viewIndex);
            // 从子tab中获得其中显示图片的ImageView
            View icon = view.findViewById(R.id.icon);
            // 获得图标的宽度
            int iconWidth = icon.getWidth();
            // 获得tab的宽度/2
            int tabWidth = view.getWidth() / 2;
            // 计算badge要距离右边的距离
            int spaceWidth = tabWidth - iconWidth;

            // 显示badegeview
            new QBadgeView(this).bindTarget(view).setGravityOffset(spaceWidth + 50, 13, false).setBadgeNumber(showNumber);
        }
    }
}
