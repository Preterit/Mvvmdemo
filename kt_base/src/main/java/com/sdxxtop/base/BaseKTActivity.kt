package com.sdxxtop.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sdxxtop.base.lifecycle.ActivityLifecycleImpl

/**
 * Email: sdxxtop@163.com
 * Created by sdxxtop 2019-07-25 15:19
 * Version: 1.0
 * Description:
 */
abstract class BaseKTActivity<DB : ViewDataBinding, VM : ViewModel> : AppCompatActivity(), IVMView<VM> {
    companion object {
        const val TAG = "BaseKTActivity"
    }

    val mBinding: DB by lazy {
        setContentView<DB>(this, layoutId())
    }

    val mViewModel: VM by lazy {
        ViewModelProviders.of(this@BaseKTActivity)[vmClazz()]

//        下面这种方式是反射获取的，有时候会比较影响性能
//        ViewModelProviders.of(this@BaseKTActivity)[getVmClass()]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(ActivityLifecycleImpl(this))

        mBinding.lifecycleOwner = this
        bindVM()
        mBinding.executePendingBindings()

        initView()
        initObserve()
        initEvent()
        initData()
        loadData()
    }

    override fun bindVM() {

    }

    override fun initEvent() {
    }

    override fun initData() {
    }

    /**
     * 不一定有页面一定要重写这个方法
     */
    override fun loadData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

//    inline fun <reified vm : ViewModel> bindViewModel(): vm {
//        return ViewModelProviders.of(this@BaseKTActivity)[vm::class.java]
//    }

    //反射获取到baseActivity上面的泛型
//    fun getVmClass(): Class<VM> {
//        return (this@BaseKTActivity::javaClass.get().genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
//    }

}