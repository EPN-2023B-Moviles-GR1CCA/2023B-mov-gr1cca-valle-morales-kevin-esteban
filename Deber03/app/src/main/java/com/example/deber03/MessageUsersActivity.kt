package com.example.deber03

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.adapter.MessageUserAdapter
import com.example.deber03.base.BaseActivity
import com.example.deber03.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class MessageUsersActivity : BaseActivity() {
    private lateinit var list: MutableList<User>
    private lateinit var ref: DatabaseReference
    private lateinit var user: FirebaseUser
    private lateinit var adapter: MessageUserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_users)
        recyclerView = findViewById(R.id.user_recycler)
        user = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().reference
        list = ArrayList()

        //User RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Function
        readUsers()
    }

    //----------------------------------Read Users--------------------------------//
    private fun readUsers() {
        ref.child(Constants.USER_CONSTANT).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataSnapshot in snapshot.children) {
                    val model = dataSnapshot.child(Constants.INFO).getValue(User::class.java)
                    if (model?.key != user.uid) {
                        model?.let { list.add(it) }
                    }
                }
                list.reverse()
                adapter = MessageUserAdapter(this@MessageUsersActivity, list)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled
            }
        })
    }
}
