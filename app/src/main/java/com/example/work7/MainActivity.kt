package com.example.work7

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val nameList = mutableListOf<String>()
    private lateinit var adapter: NameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.et_name)
        val addButton = findViewById<Button>(R.id.btn_add)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        adapter = NameAdapter(this, nameList) { position, newName ->
            nameList[position] = newName
            adapter.notifyItemChanged(position)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val name = editText.text.toString().trim()
            if (name.isNotEmpty()) {
                nameList.add(name)
                adapter.notifyItemInserted(nameList.size - 1)
                recyclerView.scrollToPosition(nameList.size - 1)
                editText.text.clear()
            }
        }
    }
}
