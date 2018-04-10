package com.test.driff.anotherdaggertest.di.component

import android.app.Application
import android.content.Context
import com.test.driff.anotherdaggertest.DemoApplication
import com.test.driff.anotherdaggertest.data.DBHelper
import com.test.driff.anotherdaggertest.data.DataManager
import com.test.driff.anotherdaggertest.data.SharedPrefsHelper
import com.test.driff.anotherdaggertest.di.ApplicationContext
import com.test.driff.anotherdaggertest.di.module.ApplicationModule
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent{


    fun inject(demoApplication: DemoApplication)

    @ApplicationContext
    fun getContext():Context

    fun getApplication():Application

    fun getDatamanager():DataManager

    fun getPrefsHelper():SharedPrefsHelper

    fun getDBHelper():DBHelper

}