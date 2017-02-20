package thanatos.kotlinmvp.presenter

import thanatos.kotlinmvp.model.IMvpModel

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：
 */
interface IMvpPresenter<V,M> {

    fun attacheView(v: V,m :M)

    fun attachedView(v: V,m :M)

    fun detacheView()
}