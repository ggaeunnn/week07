package com.example.work7

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(
    private val context: Context,
    private val nameList: MutableList<String>,
    private val onItemEdited: (Int, String) -> Unit
) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.nameTextView.text = nameList[position]

        holder.itemView.setOnClickListener {
            AlertDialog.Builder(context)
                .setMessage("${nameList[position]}을 삭제하시겠습니까?")
                .setPositiveButton("삭제") { _, _ ->
                    nameList.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("취소", null)
                .show()
        }

        holder.itemView.setOnLongClickListener {
            val editText = EditText(context).apply {
                setText(nameList[position])
            }

            AlertDialog.Builder(context)
                .setTitle("이름 편집")
                .setView(editText)
                .setPositiveButton("확인") { _, _ ->
                    val newName = editText.text.toString().trim()
                    if (newName.isNotEmpty()) {
                        onItemEdited(position, newName)
                    }
                }
                .setNegativeButton("취소", null)
                .show()
            true
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }
}
