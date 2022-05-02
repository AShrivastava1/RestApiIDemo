package com.example.composepoc

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.restapiidemo.home.data.PostModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
    fun OrderDetailListItem(orderItem: PostModel, navigateToProfile: (PostModel) -> Unit) {

    Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (orderPlacedDate, orderId, orderDetailsTitle, separator,
                    productImage, productName, status) = createRefs()

                Text(
                    text = "Order Details",
                    color = colorResource(id = com.example.restapiidemo.R.color.cta),
                    style = typography.subtitle1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(orderDetailsTitle) {
                            top.linkTo(parent.top, 8.dp)
                            end.linkTo(parent.end, 8.dp)
                        }
                        .clickable { navigateToProfile(orderItem) }
                )

                Text(
                    text = "Order Placed On : ${orderItem.date}", color = Color.Black,
                    maxLines = 1,
                    style = typography.subtitle2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .constrainAs(orderPlacedDate) {
                            top.linkTo(parent.top, 8.dp)
                            start.linkTo(parent.start, 8.dp)
                            end.linkTo(orderDetailsTitle.start, 8.dp)
                            width = Dimension.fillToConstraints
                        })

                Text(
                    text = "Order Id : ${orderItem.id}", color = Color.Black,
                    maxLines = 1,
                    style = typography.subtitle2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.constrainAs(orderId) {
                        top.linkTo(orderPlacedDate.bottom, 16.dp)
                        start.linkTo(parent.start, 8.dp)
                        width = Dimension.fillToConstraints
                    }
                )
                Spacer(
                    Modifier
                        .background(colorResource(id = com.example.restapiidemo.R.color.seperator))
                        .height(2.dp)
                        .fillMaxWidth()
                        .constrainAs(separator) {
                            top.linkTo(orderId.bottom, 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                /*Image(
                  //  painter = painterResource(id = R.drawable.ic_launcher_background),
                    painter = rememberImagePainter(orderItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(productImage) {
                            top.linkTo(separator.bottom, 8.dp)
                            start.linkTo(parent.start, 8.dp)
                            width = Dimension.fillToConstraints
                        }
                        .height(50.dp)
                        .width(50.dp)
                )*/
                GlideImage(
                    imageModel = orderItem.image,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    // shows an image with a circular revealed animation.
                    modifier = Modifier
                        .constrainAs(productImage) {
                            top.linkTo(separator.bottom, 8.dp)
                            start.linkTo(parent.start, 8.dp)
                        }
                        .height(50.dp)
                        .width(50.dp)
                )

                Text(
                    text = orderItem.title, color = Color.DarkGray,
                    maxLines = 1,
                    style = typography.subtitle2,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.constrainAs(productName) {
                        top.linkTo(separator.bottom, 8.dp)
                        start.linkTo(productImage.end, 16.dp)
                        width = Dimension.fillToConstraints
                    }
                )
                Text(
                    text = "Order Status : ${orderItem.status}", color = Color.DarkGray,
                    maxLines = 1,
                    style = typography.subtitle2,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(status) {
                            top.linkTo(productName.bottom, 16.dp)
                            start.linkTo(productImage.end, 16.dp)
                            width = Dimension.fillToConstraints
                        }
                        .padding(0.dp, 0.dp, 0.dp, 16.dp)
                )
            }
        }
    }

