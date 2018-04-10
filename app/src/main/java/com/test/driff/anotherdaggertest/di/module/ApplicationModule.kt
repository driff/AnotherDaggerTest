package com.test.driff.anotherdaggertest.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.test.driff.anotherdaggertest.di.ApplicationContext
import com.test.driff.anotherdaggertest.di.DatabaseInfo
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule constructor(private val app:Application){

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app;
    }

    @Provides
    fun application():Application{
        return app
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName():String{
        return "demo-dagger-.db"
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion():Int{
        return 2;
    }

    @Provides
    fun provideSharedPrefs():SharedPreferences{
        return app.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)
    }

}