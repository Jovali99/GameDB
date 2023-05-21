package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Platform
import com.ltu.m7019e.v23.themoviedb.model.SpecificPlatform

class Platforms {
    var list = mutableListOf<Platform>()


    init {
        list.add(Platform(SpecificPlatform(name = "PC")))
        list.add(Platform(SpecificPlatform(name = "Xbox Series S/X")))
        list.add(Platform(SpecificPlatform(name = "PlayStation 5")))
        list.add(Platform(SpecificPlatform(name = "PlayStation 4")))
        list.add(Platform(SpecificPlatform(name = "PlayStation 3")))
        list.add(Platform(SpecificPlatform(name = "Xbox 360")))
        list.add(Platform(SpecificPlatform(name = "Xbox One")))
        list.add(Platform(SpecificPlatform(name = "Nintendo Switch")))
        list.add(Platform(SpecificPlatform(name = "Linux")))
    }
}

