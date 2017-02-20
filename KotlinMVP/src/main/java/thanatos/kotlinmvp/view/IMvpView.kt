package thanatos.kotlinmvp.view

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：KotlinMVP-view接口
 */
 interface IMvpView<E>{
    fun loading()
    fun complate(entity: E)
    fun failer()
}