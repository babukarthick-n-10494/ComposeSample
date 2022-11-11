package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.composesample.R
import com.example.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {
    val viewModel: ComposeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                Surface {
                    MailDisplay()
//                        MyApp()
                }
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun MailDisplay() {
        ComposeSampleTheme {
            Surface (modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.onPrimary){
                viewModel.getMovieList()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "MyMail")
                            },
                            navigationIcon = {IconButton(onClick = { }) {
                                Icon(Icons.Filled.ArrowBack,"")
                            }},
                            backgroundColor = Color.Red,
                            contentColor = Color.White,
                            elevation = 12.dp,
                        )
                    }) {
                    LazyColumn {
                        item {

                        }
                        items(viewModel.movieListResponse) { list ->
                            MailDesign(list = list)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MailDesign(list: Movie) {
        var isExpanded by remember { mutableStateOf(false) }
        Card(shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface, modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .fillMaxWidth()
                .padding(all = 5.dp),) {
            Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background), alignment = Alignment.Center, contentDescription = "imageview", modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .size(50.dp)
                    .clip(
                        CircleShape))
                Column {
                    Row {
                        Text(text = list.name, color = MaterialTheme.colors.primary, fontSize = 16.sp, modifier = Modifier.padding(5.dp))
                        Text(text = list.imageUrl, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(5.dp))
                    }
                    Text(text = list.desc, color = MaterialTheme.colors.secondary, fontSize = 14.sp, modifier = Modifier.padding(5.dp))
                    Text(text = list.category, color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(5.dp), maxLines = if (isExpanded) Int.MAX_VALUE else 1)
                }
            }
        }

    }
}


data class Mail(val name: String, val content: String, val description: String, val time: String)

fun getList(): List<Mail> {
    val list: ArrayList<Mail> = ArrayList()
    for (i in 0 until 15) {
        list.add(Mail("babu $i", "text content ${i+1}", "description for the data of jdhjudshjzdhvjdhvjdhvjzdhjvzhxjxzhjzxjdsjjnzjdsnbdjbxjxbzxjbxjxjzzjvzxjxcjjxxjxjvjxcxjjxjxcjxjxjxjx  ${i+2}", "time $i:${i+1} am"))
    }
    return list
}
//@Preview
//@Composable
//fun MyApp(
//    modifier: Modifier = Modifier,
//    names: List<String> = listOf("World", "Compose")
//) {
//    Column(modifier = modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }
//}

//@Composable
//private fun Greeting(name: String) {
//    var isExpanded by remember { mutableStateOf(false) }
//    Surface(
//        color = MaterialTheme.colors.secondary,
//        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text(text = "Hello, ")
//                Text(text = name)
//            }
//            Button(
//                onClick = {isExpanded = !isExpanded}
//            ) {
//                Text( if (isExpanded) "Show less" else "Show more" )
//            }
//        }
//    }
//}