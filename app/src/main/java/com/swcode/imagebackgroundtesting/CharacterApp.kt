package com.swcode.imagebackgroundtesting

import android.app.Application

class CharacterApp: Application() {
    val db by lazy {
        CharacterDatabase.getInstance(this)
    }
}