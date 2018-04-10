package com.test.driff.anotherdaggertest

import android.app.Application
import android.content.Context
import com.test.driff.anotherdaggertest.data.DataManager
import com.test.driff.anotherdaggertest.di.component.ApplicationComponent
import com.test.driff.anotherdaggertest.di.component.DaggerApplicationComponent
import com.test.driff.anotherdaggertest.di.module.ApplicationModule
import javax.inject.Inject

class DemoApplication: Application() {

    companion object {
        fun get(context: Context):DemoApplication{
            return context.applicationContext as DemoApplication
        }
    }

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var dataManager: DataManager



    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

    fun getComponent():ApplicationComponent{
        return  applicationComponent
    }

}