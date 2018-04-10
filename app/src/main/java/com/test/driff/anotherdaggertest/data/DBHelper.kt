package com.test.driff.anotherdaggertest.data

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.test.driff.anotherdaggertest.data.model.User
import com.test.driff.anotherdaggertest.di.ApplicationContext
import com.test.driff.anotherdaggertest.di.DatabaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBHelper @Inject constructor(@ApplicationContext var context: Context, @DatabaseInfo var dbName: String, @DatabaseInfo var version: Int)
    : SQLiteOpenHelper(context, dbName, null, version) {

    companion object {
        const val USER_TABLE_NAME = "users"
        const val USER_COLUMN_USER_ID = "id"
        const val USER_COLUMN_USER_NAME = "usr_name"
        const val USER_COLUMN_USER_ADDRESS = "usr_add"
        const val USER_COLUMN_USER_CREATED_AT = "created_at"
        const val USER_COLUMN_USER_UPDATED_AT = "updated_at"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        tableCreateStatements(db)
    }

    private fun tableCreateStatements(db: SQLiteDatabase?) {
        try {
            db!!.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            )
        }catch ( e: SQLException) {
            e.printStackTrace()
        }
    }

    fun getUser(userId: Long): User {
        var cursor:Cursor?=null
        try{
            val db = this.readableDatabase
            cursor = db.rawQuery(
                    "SELECT * FROM "
                    + USER_TABLE_NAME
                    + " WHERE "
                    + USER_COLUMN_USER_ID
                    + " = ? ",
            arrayOf((userId).toString() + ""))

                        if (cursor.count > 0)
            {
            cursor.moveToFirst()
            return User(
                    cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)),
                    cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)),
                    cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT))
            )
            }
            else {
                throw Resources.NotFoundException("User with id $userId does not exists")
            }
        }catch (e:NullPointerException){
            e.printStackTrace()
            throw e
        } finally {
            cursor?.close()
        }
    }

    fun insertUser(user: User):Long{
        try{
            val db:SQLiteDatabase = this.writableDatabase
            val contentValues:ContentValues = ContentValues()
            contentValues.put(USER_COLUMN_USER_NAME, user.name);
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.address);
            return db.insert(USER_TABLE_NAME, null, contentValues);
        }catch (e:Exception){
            e.printStackTrace()
            throw e
        }
    }

    private fun getCurrentTimeStamp(): String {
        return (System.currentTimeMillis() / 1000).toString();
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${USER_TABLE_NAME}")
        onCreate(db)
    }

}