package com.example.deber03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.adapter.MessageAdapter
import com.example.deber03.adapter.PostAdapter
import com.example.deber03.data.Datasource
import com.example.deber03.model.Message

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postDataset = Datasource().loadPosts()
        val postRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        postRecyclerView.adapter = PostAdapter(this, postDataset)
        postRecyclerView.setHasFixedSize(true)


        val mensajes = mutableListOf(
            Message("Usuario 1", R.drawable.ic_launcher_foreground, "Hola, ¿cómo estás?"),
            Message("Usuario 2", R.drawable.ic_launcher_foreground, "Estoy bien, ¿y tú?"),
            Message("Usuario 1", R.drawable.ic_launcher_foreground, "Estoy genial, gracias por preguntar")
        )

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = MessageAdapter(mensajes)
        recyclerView.layoutManager = LinearLayoutManager(this)

       // val storyDataset = StoryData().loadStory()
       // val storyRecyclerView = findViewById<RecyclerView>(R.id.stories_container)
       // storyRecyclerView.adapter = StoryAdapter(this, storyDataset)
       // storyRecyclerView.setHasFixedSize(true)
    }
}