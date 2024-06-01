package com.vivadevs.vikipedia.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vivadevs.vikipedia.activity.MainActivity
import com.vivadevs.vikipedia.activity.NotePadActivity
import com.vivadevs.vikipedia.R
import com.vivadevs.vikipedia.db.Note
import com.vivadevs.vikipedia.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun NoteListScreen(viewModel: NoteViewModel, navController: NavHostController) {
    val allNote by viewModel.allNotes.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Text(
            text = "Vikipedia",
            color = colorResource(id = R.color.white),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
                .clickable(true, onClick = {
                    navController.context.startActivity(Intent(navController.context, MainActivity::class.java))
                }),
        )
        allNote?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) {
                            index: Int, item: Note ->
                        NoteItem(item = item, navController = navController, onDelete = {
                            viewModel.deleteNote(item.id)
                        })
                    }
                }
            )
        }?: Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            color = Color.White,
            textAlign = TextAlign.Center,
            text = "No Task Todo",
            fontSize = 20.sp
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(
                onClick = {
                    navController.context.startActivity(Intent(navController.context, NotePadActivity::class.java))
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.white)
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add", // Update the content description based on the state
                    tint = colorResource(id = R.color.my_primary)
                )
            }
        }

    }
}


@Composable
fun NoteItem(item: Note, navController: NavHostController, onDelete: ()-> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .padding(16.dp)
            .clickable(true, onClick = {
                navController.navigate("edit?id=${item.id}")
            }),

        ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.my_primary)
            )
            Text(
                text = item.content,
                fontSize = 13.sp,
                color = colorResource(id = R.color.my_primary),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column {
            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete Forever",
                    tint = colorResource(id = R.color.my_primary)
                )
            }
        }
    }
    Text(
        text = SimpleDateFormat("HH:mm:ss, dd/mm", Locale.ENGLISH).format(item.createdAt),
        fontSize = 12.sp,
        color = Color.White,
        modifier = Modifier
            .padding(start = 8.dp)
    )
}
