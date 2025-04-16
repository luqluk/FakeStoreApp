package com.android.fakestoreapp.ui.insert

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.database.AppDatabase
import com.android.fakestoreapp.database.model.TodoModel
import com.android.fakestoreapp.databinding.ActivityInsertTodoBinding
import com.android.fakestoreapp.util.DateTimeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertTodoActivity : BaseActivity<ActivityInsertTodoBinding>() {

    private val id: Long by lazy { intent.getLongExtra("id", 0) }

    override fun getViewBinding(): ActivityInsertTodoBinding =
        ActivityInsertTodoBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setListener()
    }

    private fun setupUI() {
        if (id > 0) {
            // Jika id > 0, artinya kita sedang melakukan update.
            binding.tvTitle.text = "Update Todo"
            loadTodoData(id)
        } else {
            binding.tvTitle.text = "Insert Todo"
        }
    }

    private fun loadTodoData(id: Long) {
        lifecycleScope.launch {
            val todo = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(this@InsertTodoActivity).todoDao().getById(id)
            }
            todo.let {
                binding.etTitle.setText(it.title)
                binding.etDescription.setText(it.description)
            } ?: showToast("Todo not found")
        }
    }

    private fun validate(): Boolean {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        return when {
            title.isEmpty() -> {
                showToast("Title is required")
                false
            }
            description.isEmpty() -> {
                showToast("Description is required")
                false
            }
            else -> true
        }
    }

    private fun insertTodo() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        val todo = TodoModel(
            id = 0,
            title = title,
            description = description,
            dateTime = DateTimeUtil.getCurrentDateTime()
        )

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(this@InsertTodoActivity).todoDao().insert(todo)
            }

            if (result > 0) {
                showToast("Success")
                finish()
            } else {
                showToast("Failed")
            }
        }
    }

    private fun updateTodo() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        val todo = TodoModel(
            id = id,
            title = title,
            description = description,
            dateTime = DateTimeUtil.getCurrentDateTime()
        )

        lifecycleScope.launch {
            val rowsUpdated = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(this@InsertTodoActivity).todoDao().update(todo)
            }

            if (rowsUpdated > 0) {
                showToast("Success")
                finish()
            } else {
                showToast("Failed")
            }
        }
    }

    private fun setListener() {
        binding.fabInsert.setOnClickListener {
            if (validate()) {
                if (id > 0) updateTodo() else insertTodo()
            }
        }
    }
}
