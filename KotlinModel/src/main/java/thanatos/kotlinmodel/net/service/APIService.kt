package thanatos.kotlinmodel.net.service


import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.*
import rx.Observable
import thanatos.kotlinmodel.net.entity.Response

/**
 * Created on 2017/2/17.
 * 作者：by thanatos
 * 作用：请求网络数据的API接口类
 */
interface APIService {
    /**
     * get请求
     */

    @GET("{url}")
    fun executeGetString(@Path("url") url: String): Observable<String>

    @GET("{url}")
    fun executeGetJson(@Path("url") url: String): Observable<Response>

    @GET("{url}")
     fun executeGet(@Path("url") url:String,
                   @HeaderMap head: Map<String, String>?= emptyMap()): Observable<Response>

    @GET("{url}")
    fun executeGet(@Path("url") url:String,
                   @HeaderMap head: Map<String, String>?= emptyMap(),
                   @QueryMap map: Map<String,String>?= emptyMap()
                   ): Observable<Response>

    /**
     * post请求
     */
    @POST("{url}")
    fun executePost(@Path("url") url: String,
                    @Body jsonObject: JSONObject?= JSONObject()): Observable<Response>

    @POST("{url}")
    fun executePost(@Path("url") url: String,
                    @Body jsonObject: JSONObject?= JSONObject(),
                    @HeaderMap head: Map<String, String>?= emptyMap()): Observable<Response>

    @POST("{url}")
    fun executePost(@Path("url") url: String, @FieldMap form: Map<String, String>): Observable<Response>

    /**
     * put请求
     */
//    @PUT("{url}")
//    fun executePut(@Path("url") url: String): Observable<T>
}
