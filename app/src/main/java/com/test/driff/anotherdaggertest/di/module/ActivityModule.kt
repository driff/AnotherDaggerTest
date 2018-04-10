package com.test.driff.anotherdaggertest.di.module

import android.app.Activity
import android.content.Context
import com.test.driff.anotherdaggertest.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(val activity: Activity){

    @Provides
    @ActivityContext
    fun provideContext():Context{
        return activity
    }

    @Provides
    fun activity():Activity{
        return activity
    }

}