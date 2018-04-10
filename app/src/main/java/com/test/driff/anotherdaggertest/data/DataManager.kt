package com.test.driff.anotherdaggertest.data

import android.content.Context
import android.content.res.Resources
import com.test.driff.anotherdaggertest.data.model.User
import javax.inject.Inject
import javax.inject.Singleton
import com.test.driff.anotherdaggertest.di.ApplicationContext


@Singleton
class DataManager @Inject constructor(@ApplicationContext val context:Context, val dbHelper: DBHelper, val sharedPrefsHelper: SharedPrefsHelper){

    fun saveAccessToken(accessToken:String){
        sharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    fun getAccessToken():String{
        return sharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, "")
    }

    fun createUser(user: User):Long{
        return dbHelper.insertUser(user)
    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId:Long): User {
        return dbHelper.getUser(userId)
    }

}