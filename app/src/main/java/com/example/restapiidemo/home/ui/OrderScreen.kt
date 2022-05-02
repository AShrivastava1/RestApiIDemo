package com.example.restapiidemo.home.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.example.restapiidemo.R
import com.example.restapiidemo.home.data.PostModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun OrderScreen(orderDetails: PostModel) {
    
    val mContext = LocalContext.current
    OrderPlacementAndIdView(orderDetails,mContext)

}

// Function to generate a Toast
private fun mToast(context: Context,text:String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}


@Composable
fun OrderPlacementAndIdView(orderItem: PostModel,mContext: Context) {
    Surface(color = Color.LightGray) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (columnView, orderPlacedDate, orderId,
                cardViewProdDetails, cardViewPaymentDetails) = createRefs()
            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.white))
                    .constrainAs(columnView) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Order placed : ${orderItem.date}", color = Color.Black,
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )

                Text(
                    text = "Order Id : ${orderItem.id}", color = Color.Black,
                    maxLines = 1,
//                style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            Card(
                elevation = 10.dp,
                modifier = Modifier
                    .constrainAs(cardViewProdDetails) {
                        top.linkTo(columnView.bottom)
                        start.linkTo(parent.start, 10.dp)
                        end.linkTo(parent.end, 10.dp)
                    }
                    .padding(10.dp)
            ) {
                CardElevationProductDetailsView(orderItem,mContext)
            }
            Card(
                elevation = 10.dp,
                modifier = Modifier
                    .constrainAs(cardViewPaymentDetails) {
                        top.linkTo(cardViewProdDetails.bottom)
                        start.linkTo(parent.start, 10.dp)
                        end.linkTo(parent.end, 10.dp)
                    }
                    .padding(10.dp)
            ) {
                CardElevationPaymentDetailsView()
            }
        }
    }
}


@Composable
fun CardElevationProductDetailsView(orderItem: PostModel,mContext: Context) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (productImage, productName, price, sizeColor, status,
            callout, soldByInfo, ratingColumn) = createRefs()
   /*     Image(
            painter = rememberImagePainter(orderItem.image),
            contentDescription = null,
            modifier = Modifier.constrainAs(productImage) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(parent.start, 8.dp)
            }.width(100.dp)
                .height(100.dp)
        )*/

        GlideImage(
            imageModel = orderItem.image,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            contentDescription = null,
            // shows an image with a circular revealed animation.
            modifier = Modifier.constrainAs(productImage) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(parent.start, 8.dp)
                }
                .height(100.dp)
                .width(100.dp)
        )
        Text(
            text = orderItem.title, color = Color.DarkGray,
            maxLines = 2,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(productName) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(productImage.end, 16.dp)
                end.linkTo(parent.end,8.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = "Price : ${orderItem.price}", color = Color.DarkGray,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(price) {
                top.linkTo(productName.bottom, 8.dp)
                start.linkTo(productImage.end, 16.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = "Size : ${orderItem.size}", color = Color.DarkGray,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(sizeColor) {
                top.linkTo(price.bottom, 8.dp)
                start.linkTo(productImage.end, 16.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = "Delivered On : ${orderItem.date}", color = Color.DarkGray,
            maxLines = 1,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(status) {
                top.linkTo(productImage.bottom, 4.dp)
                start.linkTo(parent.start, 8.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = orderItem.description,
            color = Color.DarkGray,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(callout) {
                top.linkTo(status.bottom, 4.dp)
                start.linkTo(parent.start, 8.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = "Sold By : TataCLiQ", color = colorResource(id = R.color.colorGrey88),
            maxLines = 1,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(soldByInfo) {
                top.linkTo(callout.bottom, 8.dp)
                start.linkTo(parent.start, 8.dp)
                width = Dimension.fillToConstraints
            }
        )
        Column(
            modifier = Modifier
                .constrainAs(ratingColumn) {
                    top.linkTo(soldByInfo.top)
                    start.linkTo(parent.start)
                }
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            RatingView(mContext)
        }
    }
}
@Composable
fun RatingView(mContext: Context) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (ratingTitle, rowRating, rowDownloadInvoice) = createRefs()
        Text(
            text = stringResource(id = R.string.your_rating),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(ratingTitle) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start)
            }
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowRating) {
                    top.linkTo(ratingTitle.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "4",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
                Image(
                    painterResource(id = R.drawable.rar_green_star),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(12.dp)
                        .width(12.dp)
                        .padding(4.dp, 2.dp, 0.dp, 0.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.rate_qualities),
                fontWeight = FontWeight.SemiBold,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 8.dp, 0.dp)
                    .clickable(onClick = {
                        mToast(
                            mContext,
                            "Rate Qualities"
                        )
                    })
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowDownloadInvoice) {
                    top.linkTo(rowRating.bottom, 16.dp)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
                .clickable(onClick = { mToast(mContext,"Download Invoice") })
        ) {
            Text(
                text = stringResource(id = R.string.download_invoice),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )
            Image(
                painterResource(id = androidx.appcompat.R.drawable.abc_ic_arrow_drop_right_black_24dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .padding(4.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }
}
@Composable
fun CardElevationPaymentDetailsView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 16.dp, 16.dp)
    ) {
        val (paymentDetailsTitle, rowSubtitle, rowDeliveryShipCharges,
            rowDiscount, rowCoupon, rowConvenienceCharges, rowCliqApplied, rowTotalAmount,
            rowPaymentMode, deliveryAddressTitle, deliveryAddress, rowHelpSupport) = createRefs()
        Text(
            text = stringResource(id = R.string.payment_details_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.constrainAs(paymentDetailsTitle) {
                top.linkTo(parent.top, 12.dp)
                start.linkTo(parent.start, 8.dp)
            }
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowSubtitle) {
                    top.linkTo(paymentDetailsTitle.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.subtotal_title),
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "Rs. 3999",
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowDeliveryShipCharges) {
                    top.linkTo(rowSubtitle.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.delivery_ship_charges_title),
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "Rs. 49.0",
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowDiscount) {
                    top.linkTo(rowDeliveryShipCharges.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.discount_title),
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "-Rs. 2720.0",
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowCoupon) {
                    top.linkTo(rowDiscount.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.coupon_title),
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "-Rs. 0.00",
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
       /* Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowConvenienceCharges) {
                    top.linkTo(rowCoupon.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.convenience_title),
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "Rs. 0.0",
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowCliqApplied) {
                    top.linkTo(rowConvenienceCharges.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.cliq_cash_applied_title),
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "-Rs. 1000",
                color = colorResource(id = R.color.colorGrey88),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }*/
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowTotalAmount) {
                    top.linkTo(rowCoupon.bottom, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.total_amount_title),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Text(
                text = "Rs. 328",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        }
        Row(horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .constrainAs(rowPaymentMode) {
                    top.linkTo(rowTotalAmount.bottom, 16.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.payment_mode_title),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Text(
                text = " Debit Card | CliQ Cash",
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        Text(
            text = stringResource(id = R.string.delivery_address_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.constrainAs(deliveryAddressTitle) {
                top.linkTo(rowPaymentMode.bottom, 16.dp)
                start.linkTo(parent.start, 8.dp)
            }
        )
        Text(
            text = "Sec 31, JMD Megapolis, Gurgaon\nHaryana-211001,",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = colorResource(id = R.color.colorGrey88),
            modifier = Modifier.constrainAs(deliveryAddress) {
                top.linkTo(deliveryAddressTitle.bottom, 8.dp)
                start.linkTo(parent.start, 8.dp)
            }
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .constrainAs(rowHelpSupport) {
                    top.linkTo(deliveryAddress.bottom, 16.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.help_support_title),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Image(
                painterResource(id = androidx.appcompat.R.drawable.abc_ic_arrow_drop_right_black_24dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .padding(4.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }
}