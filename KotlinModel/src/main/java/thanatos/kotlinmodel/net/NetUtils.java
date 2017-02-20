package thanatos.kotlinmodel.net;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import thanatos.kotlinmodel.net.service.APIService;

/**
 * Created on 2017/2/17.
 * 作者：by thanatos
 * 作用：
 */
@SuppressWarnings({"unchecked", "JavaDoc"})
public class NetUtils{

    private static final int  DEFAULT_TIMEOUT =5;
    private  APIService apiService;
    private static String mUrl;
    private  Context mContext;

    private static  NetUtils INSTANCE;
    public static final int STRING=1;
    public static final int JSON=2;
    public  int dataType;



    private NetUtils(Context context, String url, int DataType) {
        mContext=context;
        mUrl=url;
        dataType=DataType;
        config(DataType);

    }
    public static  NetUtils init(Context context, String url,int DataType) {
        if (INSTANCE==null){
            synchronized (NetUtils.class){
                if (INSTANCE==null){
                    INSTANCE=new NetUtils(context,url,DataType);
                }
            }
        }
        return INSTANCE;
    }


    private void config(int type) {
        if (TextUtils.isEmpty(mUrl)){
            throw new NullPointerException("url can not null");
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
               // .cookieJar(new TCookie(mContext))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit;
        if (type==1){
            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(mUrl)
                    .build();
        }else if (type==2){
            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(mUrl)
                    .build();
        }else {
            throw new IllegalArgumentException(" type is 1  or  2 ?");
        }
        apiService=retrofit.create(APIService.class);

    }

    public <E> void  get(String api,Subscriber<E> subscriber){
        Observable observable;
        if (dataType==1){
            observable = apiService.executeGetString(api);
        }else if (dataType==2){
            observable = apiService.executeGetJson(api);
        }else {
            observable=null;
            throw new IllegalArgumentException(" type is 1  or  2 ?");
        }
        observable.compose(schedulersTransformer())
                .subscribe(subscriber);
    }

    public <E> void  get(String api,Map<String,String > header, Subscriber<E> subscriber){
        Observable observable = apiService.executeGet(api, header);
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public <E> void get(String api,Map<String,String > header,Map<String,String > query,Subscriber<E> subscriber){
        apiService.executeGet(api,header,query).compose(schedulersTransformer()).subscribe(subscriber);
    }

    public <E> void post(String api,JSONObject body,Subscriber<E> subscriber){
        apiService.executePost(api,body).compose(schedulersTransformer()).subscribe(subscriber);
    }

    public <E> void post(String api,JSONObject body,Map<String,String> header,Subscriber<E> subscriber){
        apiService.executePost(api,body,header).compose(schedulersTransformer()).subscribe(subscriber);
    }

    public <E> void psot(String api,Map<String,String> form,Subscriber<E> subscriber){
        apiService.executePost(api,form).compose(schedulersTransformer()).subscribe(subscriber);
    }


    /**
     * 线程转换器
     * @return
     *
     */
    private Observable.Transformer schedulersTransformer(){
        return new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                Observable a= (Observable) o;
                return a.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}

