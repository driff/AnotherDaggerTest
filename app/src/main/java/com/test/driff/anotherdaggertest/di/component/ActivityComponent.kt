package com.test.driff.anotherdaggertest.di.component

import com.test.driff.anotherdaggertest.MainActivity
import com.test.driff.anotherdaggertest.di.PerActivity
import com.test.driff.anotherdaggertest.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent{

    fun inject(mainActivity: MainActivity)

}