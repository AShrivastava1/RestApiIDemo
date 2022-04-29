package com.example.restapiidemo.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.composepoc.OrderDetailListItem
import com.example.restapiidemo.home.data.PostModel

@Composable
fun OrderDetailsContent(navigateToProfile: (PostModel) -> Unit,orderItem : List<PostModel>) {

    Column(
    horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // this item is aligned to the end according to the column's alignment
        Text("Order History",
            style = typography.subtitle1,
            fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(
                items = orderItem,
                itemContent = {
                    OrderDetailListItem(orderItem = it, navigateToProfile)
                }
            )
        }
    }


}
