package thanatos.kotlinmvp.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_mvp.*
import thanatos.kotlinmvp.R
import thanatos.kotlinmvp.presenter.MvpPresenter

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：KotlinMVP-view-baseActivity基类
 */
abstract class MvpActivity<V,P : MvpPresenter<V,M>,M> : AppCompatActivity() {

    /**presenter实例*/
    protected var mPresenter:P?=null

    protected var mSwipeRefreshLayout:SwipeRefreshLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        swipeRefreshLayout.setColorSchemeResources(R.color.themeColor)
        this.mSwipeRefreshLayout=swipeRefreshLayout
        mPresenter=initPresenter()
        mPresenter?.attacheView(this as V,initModel())
    }


    /**
    * 初始化presenter
     */
    abstract fun initPresenter(): P
    abstract fun initModel(): M

    /**
    * 子类加载布局
     * 注意：重复初始化布局的时候不可使用
     * 后果：不会加载修改后的数据，只会显示初始化数据
     */
    fun setContent(resId: Int){
        removeContentAllViews()
        content.addView(View.inflate(this,resId,null))
    }

    /**
    * 子类加载布局或者控件
     * 可重复初始化布局
     */
    fun setContent(view: View){
        removeContentAllViews()
        content.addView(view)
    }

    /**
    * 设置子类是否需要toolBar
     */
    fun showToolBar(show: Boolean){
        if (show){
            if (toolBar.visibility!=View.VISIBLE){
                toolBar.visibility=View.VISIBLE
            }
        }else{
            if (toolBar.visibility==View.VISIBLE ||toolBar.visibility==View.INVISIBLE){
                toolBar.visibility=View.GONE
            }
        }
    }

    /**
    * 设置子类是否需要下拉刷新控件
     */
    fun swipeRefreshLayoutCanUse(use: Boolean){
        swipeRefreshLayout.isEnabled=use
    }


    fun refreshListener(){
        swipeRefreshLayout.isRefreshing=true
    }


    private fun removeContentAllViews(){
        if (content.childCount>0){
            content.removeAllViews()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter!=null){
            mPresenter?.detacheView()
            mPresenter=null
        }
    }


}
