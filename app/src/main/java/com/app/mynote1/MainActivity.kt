package com.app.mynote1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.mynote1.ui.theme.Mynote1Theme

class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mynote1Theme {
                NoteApp(viewModel = noteViewModel)
            }
        }
    }
}

@Composable
fun NoteApp(viewModel: NoteViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Mynote1") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addNote("Note ${'$'}{viewModel.notes.size + 1}") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        NoteList(notes = viewModel.notes, modifier = Modifier.fillMaxSize())
    }
}
