package thanatos.kotlinmvp.utils

import android.content.Context
import android.content.SharedPreferences
import java.io.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created on 2017/2/16.
 * 作者：by thanatos
 * 作用：
 */
class Preference<T> constructor(val context: Context){

    private val sp: SharedPreferences by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        context.getSharedPreferences("config",Context.MODE_PRIVATE)
    }

    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
     fun getValue(key: String, default: T): T{
         return findValue(key,default)
    }


    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
     fun setValue(key: String, value: T) {
         putValue(key, value)
    }

    /**
    * 查找数据
     */
    @Suppress("UNCHECKED_CAST")
    private fun <A> findValue(key: String, default: A) : A = with(sp){
        val res : Any = when(default){
            is Long -> getLong(key,default)
            is String -> getString(key,default)
            is Int -> getInt(key,default)
            is Boolean -> getBoolean(key,default)
            is Float -> getFloat(key,default)
            else -> ""
        }
        res as A
    }

    /**
    * 保存数据
     */
    private fun <A> putValue(key: String, value: A) {

        when(value){
            is Long -> sp.edit().putLong(key,value).apply()
            is String -> sp.edit().putString(key,value).apply()
            is Int -> sp.edit().putInt(key,value).apply()
            is Boolean -> sp.edit().putBoolean(key,value).apply()
            is Float -> sp.edit().putFloat(key,value).apply()
            else -> sp.edit().putString(key, value.toString()).apply()
        }

    }

    /**
     * 删除全部数据
     */
    fun clearAll(){
        sp.edit().clear().apply()
    }

    /**
     * 根据key删除存储数据
     */
    fun clear(key : String){
        sp.edit().remove(key).apply()
    }

}