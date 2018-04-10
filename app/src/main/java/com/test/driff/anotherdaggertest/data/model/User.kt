package com.test.driff.anotherdaggertest.data.model

data class User constructor(var id:Long?, var name: String, var address: String, var createdAt: String?, var updatedAt:String?) {

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\''.toString() +
                ", address='" + address + '\''.toString() +
                ", createdAt='" + createdAt + '\''.toString() +
                ", updatedAt='" + updatedAt + '\''.toString() +
                '}'.toString()
    }
}