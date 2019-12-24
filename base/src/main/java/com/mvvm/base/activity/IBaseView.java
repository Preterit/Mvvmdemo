package com.mvvm.base.activity;

/**
 * Date:2019-12-24
 * author:lwb
 * Desc:
 */
public interface IBaseView {
    void showContent();

    void showLoading();

    void onRefreshEmpty();

    void onRefreshFailure(String message);
}
