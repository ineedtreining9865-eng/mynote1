package com.app.mynote1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.mynote1.ui.theme.Mynote1Theme
import com.app.mynote1.ui.TodoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mynote1Theme {
                TodoScreen()
            }
        }
    }
}
