package thanatos.kotlinmvp.presenter

import android.view.View
import thanatos.kotlinmvp.model.IMvpModel
import java.lang.ref.WeakReference

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：KotlinMVP-presenter基类
 */
abstract class MvpPresenter<V,M> : IMvpPresenter<V,M>{
    private var viewWRF:WeakReference<V>?=null
    private var modelWRF:WeakReference<M>?=null
     override fun attacheView(v: V,m:M) {
         viewWRF= WeakReference<V>(v)
         modelWRF= WeakReference<M>(m)
        attachedView(viewWRF?.get() as V,modelWRF?.get() as M)
    }

    override abstract fun attachedView(v: V,m: M)

    override fun detacheView() {
        if (viewWRF!=null){
            viewWRF?.clear()
            viewWRF=null
        }
        if (modelWRF!=null){
            modelWRF?.clear()
            modelWRF=null
        }
    }
}