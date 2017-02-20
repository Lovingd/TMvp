package thanatos.kotlinmvptest.model

import rx.Subscriber
import thanatos.kotlinmodel.net.NetUtils
import thanatos.kotlinmvp.model.IMvpModel
import thanatos.kotlinmvp.utils.TLog
import thanatos.kotlinmvptest.MyApplicaiont
import thanatos.kotlinmvptest.data.User

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：
 */
 class MainModel : IMvpModel<String> {
    override fun getData(listener: IMvpModel.FinishListener<String>, vararg args: Any) {
        MyApplicaiont.net.get("index.html",object :Subscriber<String>(){
            override fun onNext(t: String?) {
                listener.complate(t!!)
            }


            override fun onError(e: Throwable?) {
                listener.failer(e!!)
            }


            override fun onCompleted() {

            }

        })


    }
}
