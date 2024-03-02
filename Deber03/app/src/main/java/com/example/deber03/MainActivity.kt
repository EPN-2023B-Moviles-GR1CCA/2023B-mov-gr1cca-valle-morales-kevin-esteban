package com.example.deber03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.adapter.PostAdapter
import com.example.deber03.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postDataset = Datasource().loadPosts()
        val postRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        postRecyclerView.adapter = PostAdapter(this, postDataset)
        postRecyclerView.setHasFixedSize(true)

    }
}