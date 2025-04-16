package com.android.fakestoreapp.ui.main.adapter

import android.view.View
import com.android.fakestoreapp.base.BaseListAdapter
import com.android.fakestoreapp.database.model.TodoModel
import com.android.fakestoreapp.databinding.ListTodoItemBinding

class TodoAdapter : BaseListAdapter<TodoModel, ListTodoItemBinding>(
    ListTodoItemBinding::inflate
) {
    private var listener: ((TodoModel) -> Unit)? = null
    private var deleteListener: ((TodoModel) -> Unit)? = null
    override fun onItemBind(): (TodoModel, ListTodoItemBinding, View, Int) -> Unit =
        { item, binding, _, _ ->
            binding.apply {
                tvTitle.text = item.title
                tvDescription.text = item.description
                tvDateTime.text = item.dateTime
                root.setOnClickListener {
                    listener?.invoke(item)
                }
                ivDelete.setOnClickListener {
                    deleteListener?.invoke(item)
                }
            }
        }

    fun setOnItemClickListener(listener: (TodoModel) -> Unit) {
        this.listener = listener
    }

    fun setOnDeleteClickListener(listener: (TodoModel) -> Unit) {
        this.deleteListener = listener
    }

}
