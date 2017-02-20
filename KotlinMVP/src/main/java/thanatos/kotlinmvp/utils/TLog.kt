package thanatos.kotlinmvp.utils

/**
 * Created on 2017/2/16.
 * 作者：by thanatos
 * 作用：日志工具类
 */

class TLog private constructor(show: Boolean){

    companion object{
        private var mShow: Boolean = true
        fun init(show: Boolean): TLog {
            mShow=show
            return  TLog(show)
        }

        fun i(msg: String, clazz: Class<*>?=null, tag: String="thanatos"){
            if (mShow){
                temp("I",tag,msg,clazz)
            }

        }

        fun w(msg: String, clazz: Class<*>?=null, tag: String="thanatos"){
            if (mShow){
                temp("W",tag,msg,clazz)
            }

        }

        fun e(msg: String, clazz: Class<*>?=null, tag: String="thanatos"){
            if (mShow){
                temp("E",tag,msg,clazz)
            }
        }

        private fun temp(t: String,tag: String, msg: String,clazz: Class<*>?=null){
            val sb: StringBuilder= StringBuilder()
            sb.append("===作者：     "+tag+"============开始===========\n")
            sb.append("   LOG等级：  "+t+"\n")
            sb.append("------------------------------------------------\n")
            sb.append("   当前线程： "+Thread.currentThread().name+"\n")
            sb.append("------------------------------------------------\n")
            sb.append("   当前类：   "+clazz.toString()+"\n")
            sb.append("------------------------------------------------\n")
            sb.append("   内容:      "+msg+"\n")
            sb.append("=======================结束======================\n")
            println(sb.toString())
        }
    }

}
