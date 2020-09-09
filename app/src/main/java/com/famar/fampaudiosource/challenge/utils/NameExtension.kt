package com.famar.fampaudiosource.challenge.utils

import com.famar.fampaudiosource.challenge.model.Name

fun formatName(name: Name) : String {
    return name.first + name.last
}