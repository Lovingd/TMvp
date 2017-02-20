package thanatos.kotlinmodel.net.cookie

import android.content.Context

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

import java.net.CookieStore

/**
 * Created on 2017/2/17.
 * 作者：by thanatos
 * 作用：Cookie管理器
 */
class TCookie constructor(val context: Context):CookieJar{

    private val cookieStore: PersistentCookieStore by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        PersistentCookieStore(context)
    }

    /**
     * Saves `cookies` from an HTTP response to this store according to this jar's policy.

     *
     * Note that this method may be called a second time for a single HTTP response if the response
     * includes a trailer. For this obscure HTTP feature, `cookies` contains only the trailer's
     * cookies.
     */
    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
        if (cookies!=null && cookies.size>0){
            for (item in cookies){
                cookieStore.add(url,item)
            }
        }
    }

    /**
     * Load cookies from the jar for an HTTP request to `url`. This method returns a possibly
     * empty list of cookies for the network request.

     *
     * Simple implementations will return the accepted cookies that have not yet expired and that
     * [match][Cookie.matches] `url`.
     */
    override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> =cookieStore.get(url)
}