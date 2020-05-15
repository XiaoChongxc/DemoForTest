package demo.test.xc.com.demo.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.arch.core.util.Function
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import demo.test.xc.com.demo.R
import demo.test.xc.com.demo.entity.UserInfo
import demo.test.xc.com.demo.kotlin.TestViewModel
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity :FragmentActivity () {

    companion object {
        fun startThis(context: Context) {
            var intent = Intent(context, TestActivity::class.java)
            context.startActivity(intent);
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val testViewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        testViewModel.userData.observe(this, Observer {
            tv_content.text = "姓名：${it.name}\n工资:${it.age}"
        })

        btn_update.setOnClickListener {
            testViewModel.getUserInfo()

        }
    }
}
