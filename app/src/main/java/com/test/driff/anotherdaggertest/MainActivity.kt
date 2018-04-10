package com.test.driff.anotherdaggertest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.driff.anotherdaggertest.data.DataManager
import com.test.driff.anotherdaggertest.data.model.User
import com.test.driff.anotherdaggertest.di.component.ActivityComponent
import com.test.driff.anotherdaggertest.di.component.DaggerActivityComponent
import com.test.driff.anotherdaggertest.di.module.ActivityModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager

    private lateinit var activityComponent:ActivityComponent

    private var tvUserInfo: TextView? = null
    private var tvAccessToken: TextView? = null

    fun getActivityComponent(): ActivityComponent {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(DemoApplication.get(this).getComponent())
                .build()
        return activityComponent;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getActivityComponent().inject(this)

        tvUserInfo = findViewById(R.id.tv_user_info) as TextView
        tvAccessToken = findViewById(R.id.tv_access_token) as TextView
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        createUser()
        getUser()
        dataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543")

        val token:String? = dataManager.getAccessToken()
        if (token != null) {
            tvAccessToken?.text = token
        }
    }

    private fun getUser() {
        val user:User = dataManager.getUser(1L)
        tvUserInfo?.text = user.toString()
    }

    private fun createUser() {
        dataManager.createUser(User(null, "Ali", "1367, Gurgaon, Haryana, India", null, null))
    }

}
