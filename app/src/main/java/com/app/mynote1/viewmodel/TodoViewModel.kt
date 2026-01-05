package com.app.mynote1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class TodoItem(val id: Int, val title: String, var done: Boolean = false)

data class Category(val id: Int, val name: String, val todos: MutableList<TodoItem> = mutableStateListOf())

class TodoViewModel : ViewModel() {
    private var categoryIdCounter = 0
    private var todoIdCounter = 0

    val categories = mutableStateListOf<Category>()

    fun addCategory(name: String) {
        if (name.isBlank()) return
        categories.add(Category(id = categoryIdCounter++, name = name))
    }

    fun addTodo(categoryId: Int, title: String) {
        if (title.isBlank()) return
        val category = categories.find { it.id == categoryId } ?: return
        category.todos.add(TodoItem(id = todoIdCounter++, title = title))
    }

    fun toggleTodoDone(categoryId: Int, todoId: Int) {
        val category = categories.find { it.id == categoryId } ?: return
        val todo = category.todos.find { it.id == todoId } ?: return
        todo.done = !todo.done
    }
}
