package com.example.deber03.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.model.Message
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.deber03.R

class MessageAdapter(private val mensajes: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mensaje, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mensaje = mensajes[position]
        holder.nombreUsuarioTextView.text = mensaje.nombreUsuario
        holder.fotoPerfilImageView.setImageDrawable(mensaje.fotoPerfil)
        holder.vistaPreviaMensajeTextView.text = mensaje.vistaPreviaMensaje
    }

    override fun getItemCount() = mensajes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreUsuarioTextView: TextView = itemView.findViewById(R.id.nombre_usuario_textView)
        val fotoPerfilImageView: ImageView = itemView.findViewById(R.id.foto_perfil_imageView)
        val vistaPreviaMensajeTextView: TextView = itemView.findViewById(R.id.vista_previa_mensaje_textView)
    }
}
