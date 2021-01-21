package com.example.myfirstapplication.service.Arg

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object BooleanArg: ReadWriteProperty<Bundle, Boolean?> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Boolean?) {
        thisRef.putBoolean(property.name, value!!)
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): Boolean {
        return thisRef.getBoolean(property.name)
    }
}