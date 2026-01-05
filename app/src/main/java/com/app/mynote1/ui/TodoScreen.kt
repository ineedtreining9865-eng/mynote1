package com.app.mynote1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.mynote1.viewmodel.Category
import com.app.mynote1.viewmodel.TodoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(todoViewModel: TodoViewModel = viewModel()) {
    var categoryInput by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mynote1") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Row {
                    OutlinedTextField(
                        value = categoryInput,
                        onValueChange = { categoryInput = it },
                        label = { Text("New Category") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        todoViewModel.addCategory(categoryInput)
                        categoryInput = ""
                    }) {
                        Text("Add")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(todoViewModel.categories) { category ->
                        CategoryItem(category = category, viewModel = todoViewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun CategoryItem(category: Category, viewModel: TodoViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var todoInput by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null
                    )
                }
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value = todoInput,
                        onValueChange = { todoInput = it },
                        label = { Text("New Todo") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        viewModel.addTodo(category.id, todoInput)
                        todoInput = ""
                    }) {
                        Text("Add")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    category.todos.forEach { todo ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = todo.done,
                                onCheckedChange = {
                                    viewModel.toggleTodoDone(category.id, todo.id)
                                }
                            )
                            Text(
                                text = todo.title,
                                style = if (todo.done) MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                ) else MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
