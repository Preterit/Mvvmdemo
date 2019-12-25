package com.mvvm.base.fragment;

import com.mvvm.base.activity.IBaseView;

/**
 * @author :  lwb
 * Date: 2019/12/25
 * Desc:
 */
public interface IBasePagingView extends IBaseView {

    void onLoadMoreFailure(String message);

    void onLoadMoreEmpty();
}
