package com.example.deber03.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import android.graphics.drawable.Drawable

data class Message (
     val nombreUsuario: String,
     val fotoPerfil: Drawable,
     val vistaPreviaMensaje: String
    )
