package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val observer = Observer<Event<Unit>> {}
    private lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setUp() {
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {

        tasksViewModel.newTaskEvent.observeForever(observer)

        tasksViewModel.addNewTask()

        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()

        assertThat(value, `is`(true))
    }
}