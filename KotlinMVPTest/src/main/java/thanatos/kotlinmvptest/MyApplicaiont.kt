package thanatos.kotlinmvptest

import android.app.Application
import thanatos.kotlinmodel.net.NetUtils

/**
 * Created on 2017/2/17.
 * 作者：by thanatos
 * 作用：
 */
class MyApplicaiont  : Application(){


   companion object{
       private val application=MyApplicaiont()
       val net:NetUtils =NetUtils.init(application,"http://shoujidaoyou.cn/weixinceshi/",NetUtils.STRING)

   }
    override fun onCreate() {
        super.onCreate()

    }
}