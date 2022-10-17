package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesample.R
import com.example.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                Surface {
                    MailDisplay()

                }
            }
        }
    }
}

@Preview
@Composable
fun MailDisplay() {
    ComposeSampleTheme {
        Surface (modifier = Modifier.fillMaxWidth()){
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "MyMail")
                        },
                        backgroundColor = Color.Red,
                        contentColor = Color.White,
                        elevation = 12.dp
                    )
                }) {
                LazyColumn {
                    items(getList()) { list ->
                        MailDesign(list = list)
                    }
                }
            }
        }
    }
}

@Composable
fun MailDesign(list: Mail) {
    Card(shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface, modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)){
            Image(painter = painterResource(id = R.drawable.test), alignment = Alignment.Center, contentDescription = "imageview", modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .size(50.dp)
                .clip(
                    CircleShape))
            Column() {
                Text(text = list.name, color = MaterialTheme.colors.primary, fontSize = 16.sp, modifier = Modifier.padding(5.dp))
                Text(text = list.content, color = MaterialTheme.colors.secondary, fontSize = 14.sp, modifier = Modifier.padding(5.dp))
                Text(text = list.description, color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(5.dp))
            }
            Column() {
                Text(text = list.time, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(5.dp))
            }
        }
    }

}

data class Mail(val name: String, val content: String, val description: String, val time: String)

fun getList(): List<Mail> {
    val list: ArrayList<Mail> = ArrayList()
    for (i in 0 until 15) {
        list.add(Mail("babu $i", "text content ${i+1}", "description for the sample data ${i+2}", "time $i:${i+1} am"))
    }
    return list
}