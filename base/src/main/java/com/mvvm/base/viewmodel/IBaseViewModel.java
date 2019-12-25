package com.mvvm.base.viewmodel;

/**
 * Date:2019-12-24
 * author:lwb
 * Desc:
 */
public interface IBaseViewModel<V> {
    void attachUI(V view);

    V getPageView();

    boolean isUIAttached();

    void detachUI();
}
