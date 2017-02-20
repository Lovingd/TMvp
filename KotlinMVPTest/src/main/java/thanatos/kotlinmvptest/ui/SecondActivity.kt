package thanatos.kotlinmvptest.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import thanatos.kotlinmvptest.R
import kotlin.concurrent.thread

class SecondActivity : AppCompatActivity() {
    private var view:View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view= View.inflate(this,R.layout.activity_second,null)
        setContentView(R.layout.activity_second)
        tv.text="hehe"

        thread {
            Thread.sleep(2000)
            runOnUiThread {
                setContentView(R.layout.activity_second)
                tv.text="haha"
            }
        }
    }
}
