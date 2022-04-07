package com.learning.ttextsaver

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.learning.ttextsaver.ui.theme.TTextSaverTheme
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TTextSaverTheme(isSystemInDarkTheme()) {

 Button1()
        Button2()
            }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TTextSaverTheme {
        Button1()
        Button2()
    }
}



@Composable
fun Button1(){
    Column(modifier = Modifier.fillMaxSize()
    ,horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {


                    var firstname by remember { mutableStateOf("")}
                    TextField(value = firstname,
                        onValueChange = {firstname= it})
        Row(verticalAlignment = Alignment.Bottom) {


                    Button(onClick = {


                                if(firstname != ""){

                                // For First time: Creates a text file and writes string into it

                                // Else: Opens the text file and writes the string

                                try {
                                    val fileOutputStream = openFileOutput("TextFile.txt", Context.MODE_PRIVATE)
                                    val outputWriter = OutputStreamWriter(fileOutputStream)
                                    outputWriter.write(firstname)
                                    outputWriter.close()
                                    Toast.makeText(baseContext, "Text saved successfully!", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }

                            } else {
                                Toast.makeText(applicationContext, "No input?", Toast.LENGTH_SHORT).show()
                            }
                        }
                                    ) {
                                    Text(text = "Submit")

                                    }
        }}}


@Composable
fun Button2(){
            Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {


                            var lastname by remember { mutableStateOf("")}
                            TextField(value = lastname,
                                onValueChange = { lastname = it },
                            readOnly = true)
                            Row(verticalAlignment = Alignment.Bottom) {


                                Button(onClick = {
                                    // Tries to fetch data from the text file

                                        try {
                                            val fileInputStream = openFileInput("TextFile.txt")
                                            val inputReader = InputStreamReader(fileInputStream)
                                            val output = inputReader.readText()

                                            // Data is displayed in the TextField

                                            lastname = output
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                }
                                ) {
                                    Text(text = "Show Text")
            }
                }}}

}






