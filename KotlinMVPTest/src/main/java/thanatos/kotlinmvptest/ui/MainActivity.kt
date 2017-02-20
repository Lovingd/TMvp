package thanatos.kotlinmvptest.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

import thanatos.kotlinmodel.net.Images

import thanatos.kotlinmvp.utils.TLog
import thanatos.kotlinmvp.view.IMvpView
import thanatos.kotlinmvp.view.MvpActivity
import thanatos.kotlinmvptest.ListViewAdapter
import thanatos.kotlinmvptest.R
import thanatos.kotlinmvptest.model.MainModel
import thanatos.kotlinmvptest.presenter.MainPresenter

class MainActivity : MvpActivity<IMvpView<String>,MainPresenter,MainModel>(),IMvpView<String> {

    private var view: View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        view= View.inflate(this,R.layout.activity_main,null)
        super.onCreate(savedInstanceState)
        showToolBar(true)
        swipeRefreshLayoutCanUse(false)
        mSwipeRefreshLayout?.setOnRefreshListener {
            mPresenter?.attachedView(this as IMvpView<String>,initModel())
        }
    }

    /**
     * 初始化presenter
     *
     */
    override fun initPresenter(): MainPresenter = MainPresenter()

    override fun initModel(): MainModel = MainModel()

    override fun loading() {
        val loading:ProgressBar= ProgressBar(this)
        setContent(loading)
        mSwipeRefreshLayout?.isRefreshing=true
    }

    override fun complate(entity: String) {
        val v: View= view!!
        setContent(v)
        mSwipeRefreshLayout?.isRefreshing=false
        TLog.w(entity)
        tv.text="hehe"
        val imageThumbUrls = Images.imageThumbUrls
        lv.adapter=ListViewAdapter(this,imageThumbUrls)

    }

    override fun failer() {
    }

}

