package com.mupper.personlist.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mupper.personlist.databinding.ActivityMainBinding
import com.mupper.personlist.presentation.ui.adapter.PersonsAdapter
import com.mupper.personlist.presentation.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val personViewModel: PersonViewModel by viewModels()

    private lateinit var personsAdapter: PersonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            initMainToolbar()
            initRecyclerView()
            initSwipeToRefresh()
            retrievePersons()
        }
    }

    private fun ActivityMainBinding.initMainToolbar() {
        setSupportActionBar(mainToolbar)
    }

    private fun ActivityMainBinding.initRecyclerView() {
        personsAdapter = PersonsAdapter()
        personsRecyclerView.apply {
            adapter = personsAdapter
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            val dividerItemDecoration = DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
        }
        lifecycleScope.launchWhenCreated(collectPersonsFromViewModel)
    }

    private fun ActivityMainBinding.initSwipeToRefresh() {
        personsSwipeToRefresh.setOnRefreshListener {
            retrievePersons()
        }
    }

    private fun ActivityMainBinding.retrievePersons() {
        lifecycleScope.launch {
            personViewModel.retrievePersons()
            personsSwipeToRefresh.isRefreshing = false
        }
    }

    private val collectPersonsFromViewModel: suspend CoroutineScope.() -> Unit = {
        personViewModel.getPersons().collect {
            personsAdapter.submitList(it)
        }
    }
}