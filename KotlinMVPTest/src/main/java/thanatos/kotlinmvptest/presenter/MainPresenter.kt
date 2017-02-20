package thanatos.kotlinmvptest.presenter

import thanatos.kotlinmvp.model.IMvpModel
import thanatos.kotlinmvp.presenter.MvpPresenter
import thanatos.kotlinmvp.view.IMvpView
import thanatos.kotlinmvptest.model.MainModel

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：
 */
class MainPresenter : MvpPresenter<IMvpView<String>,MainModel>() {
    override fun attachedView(v: IMvpView<String>, m: MainModel) {
        m.getData(object : IMvpModel.FinishListener<String>{
            override fun complate(data: String) {
                v.complate(data)
            }

            override fun failer(e: Any) {
                v.failer()
            }
        },1,1,1)
    }

}