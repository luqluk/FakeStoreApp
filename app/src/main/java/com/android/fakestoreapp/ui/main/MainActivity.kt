package com.android.fakestoreapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.core.util.mapper.toRocketListDomainModel
import com.android.core.util.resource.ResourceState
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.database.AppDatabase
import com.android.fakestoreapp.database.model.TodoModel
import com.android.fakestoreapp.databinding.ActivityMainBinding
import com.android.fakestoreapp.ui.insert.InsertTodoActivity
import com.android.fakestoreapp.ui.main.adapter.TodoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    // Use Koin's viewModel delegate to inject the ViewModel
    private val mainActivityVM: MainActivityVM by viewModel()

    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityVM.fetchListRocket()
        observeLivedata()
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initListener()
    }

    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            navigateToDetail(0)
        }
    }

    private fun initRecyclerView() {
        with(binding){
            rvTodo.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = todoAdapter.apply {
                    setOnItemClickListener {
                        navigateToDetail(it.id)
                    }
                    setOnDeleteClickListener {
                        AppDatabase.getInstance(this@MainActivity).todoDao().delete(it)
                        todoAdapter.submitList(loadData())
                        showToast("Success")
                    }
                }

            }
        }

        todoAdapter.submitList(loadData())
    }

    private fun navigateToDetail(id: Long) {
        val intent = Intent(this, InsertTodoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun loadData(): List<TodoModel> {
        val data = AppDatabase.getInstance(this).todoDao().getAll()
        return data
    }

    private fun observeLivedata(){
        mainActivityVM.listRocket.observe(this){
            when(it.resourceState) {
                is ResourceState.Loading -> {
                    this.showBaseDialog(false)
                }
                is ResourceState.Success -> {
                    this.dismissBaseDialog()
                    if (it.data != null) {
                        val data = it.data?.toRocketListDomainModel()
                    }

                }
                is ResourceState.Error.UnknownHost -> {
                    this.dismissBaseDialog()
                    showToast("There is no connection")
                }
                else -> {
                    this.dismissBaseDialog()
                    showToast("Something went wrong")
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        todoAdapter.submitList(loadData())

    }

}