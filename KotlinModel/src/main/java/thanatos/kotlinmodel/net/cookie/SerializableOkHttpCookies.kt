package thanatos.kotlinmodel.net.cookie

import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

import okhttp3.Cookie

/**
 * Created on 2017/2/17.
 * 作者：by thanatos
 * 作用：
 */
class SerializableOkHttpCookies(@Transient private val cookies: Cookie) : Serializable {
    @Transient private var clientCookies: Cookie? = null

    fun getCookies(): Cookie {
        var bestCookies = cookies
        if (clientCookies != null) {
            bestCookies = clientCookies as Cookie
        }
        return bestCookies
    }

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.writeObject(cookies.name())
        out.writeObject(cookies.value())
        out.writeLong(cookies.expiresAt())
        out.writeObject(cookies.domain())
        out.writeObject(cookies.path())
        out.writeBoolean(cookies.secure())
        out.writeBoolean(cookies.httpOnly())
        out.writeBoolean(cookies.hostOnly())
        out.writeBoolean(cookies.persistent())
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(item: ObjectInputStream) {
        val name = item.readObject() as String
        val value = item.readObject() as String
        val expiresAt = item.readLong()
        val domain = item.readObject() as String
        val path = item.readObject() as String
        val secure = item.readBoolean()
        val httpOnly = item.readBoolean()
        val hostOnly = item.readBoolean()
        val persistent = item.readBoolean()
        var builder = Cookie.Builder()
        builder = builder.name(name)
        builder = builder.value(value)
        builder = builder.expiresAt(expiresAt)
        builder = if (hostOnly) builder.hostOnlyDomain(domain) else builder.domain(domain)
        builder = builder.path(path)
        builder = if (secure) builder.secure() else builder
        builder = if (httpOnly) builder.httpOnly() else builder
        clientCookies = builder.build()
    }
}
