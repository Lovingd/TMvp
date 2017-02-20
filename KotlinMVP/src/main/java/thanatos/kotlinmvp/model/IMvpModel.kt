package thanatos.kotlinmvp.model

/**
 * Created on 2017/2/15.
 * 作者：by thanatos
 * 作用：
 */
 interface IMvpModel<E> {
    fun getData(listener: FinishListener<E>,vararg args:Any)
    interface FinishListener<E>{
        fun complate(data: E)
        fun failer(e: Any)
    }
}