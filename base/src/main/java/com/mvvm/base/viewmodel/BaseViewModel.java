package com.mvvm.base.viewmodel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

/**
 * @author :  lwb
 * Date: 2019/12/25
 * Desc:
 */
public class BaseViewModel<V> extends ViewModel implements IBaseViewModel<V> {

    private Reference<V> mUIRef;

    public void attachUI(V ui) {
        mUIRef = new WeakReference<>(ui);
    }

    @Nullable
    public V getPageView() {
        if (mUIRef == null) {
            return null;
        }
        return mUIRef.get();
    }

    public boolean isUIAttached() {
        return mUIRef != null && mUIRef.get() != null;
    }

    public void detachUI() {
        if (mUIRef != null) {
            mUIRef.clear();
            mUIRef = null;
        }
    }
}
