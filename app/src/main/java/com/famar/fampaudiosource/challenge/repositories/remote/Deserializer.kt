package com.famar.fampaudiosource.challenge.repositories.remote

import com.famar.fampaudiosource.challenge.model.Name
import com.famar.fampaudiosource.challenge.model.User
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class Deserializer : JsonDeserializer<User> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): User? {
        json?.let {
            if (it.isJsonPrimitive) {
                it.asJsonPrimitive.let { jsonPrimitive ->
                    if (jsonPrimitive.isString) {
                        val profile = Name(title = jsonPrimitive.asString)
                        return User(name = profile)
                    }
                }
            }

            return Gson().fromJson(json, User::class.java)
        } ?: kotlin.run {
            return null
        }
    }
}