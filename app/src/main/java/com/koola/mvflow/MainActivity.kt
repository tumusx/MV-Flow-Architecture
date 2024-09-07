package com.koola.mvflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.koola.mvflow.flow.DefaultContentSuccess
import com.koola.mvflow.flow.DefaultFlow
import com.koola.mvflow.flow.Interaction
import com.koola.mvflow.flow.SecondaryContentSuccess
import com.koola.mvflow.flow.SecondaryFlow
import com.koola.mvflow.flow.State
import com.koola.mvflow.ui.theme.MVFlowTheme

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel(SecondaryFlow())
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVFlowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state = viewModel.state.collectAsState().value

                    Greeting(
                        name = (state as? State.Content<SecondaryContentSuccess>)?.content?.nameUser
                            ?: "Teste",
                        modifier = Modifier
                            .padding(innerPadding)
                            .clickable {
                                viewModel.onHandleUser(Interaction.EventClick("SEE_USER"))
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVFlowTheme {
        Greeting("Android")
    }
}