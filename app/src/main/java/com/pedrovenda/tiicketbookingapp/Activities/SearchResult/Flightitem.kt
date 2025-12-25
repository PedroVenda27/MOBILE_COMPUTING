package com.pedrovenda.tiicketbookingapp.Activities.SearchResult

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.pedrovenda.tiicketbookingapp.Activities.Domain.FlightModel
import com.pedrovenda.tiicketbookingapp.Activities.SeatSelect.SeatSelectActivity
import com.pedrovenda.tiicketbookingapp.R

@Composable
fun FlightItem(item: FlightModel, index: Int) {
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.lightPurple),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp)
            )
            .clickable {
                val intent = Intent(context, SeatSelectActivity::class.java).apply {
                    putExtra("flight", item)
                }
                startActivity(context, intent, null)
            }
            .padding(12.dp)
    ) {

        val (
            logo, timeTxt, airplaneIcon, dashLine,
            priceTxt, seatIcon, classTxt,
            fromTxt, fromShortTxt, toTxt, toShortTxt
        ) = createRefs()

        // LOGO
        AsyncImage(
            model = item.AirlineLogo,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 50.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // TIME
        Text(
            text = item.ArriveTime,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(timeTxt) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // FROM
        Text(
            text = item.From,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromTxt) {
                    top.linkTo(timeTxt.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = item.FromShort,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromShortTxt) {
                    top.linkTo(fromTxt.bottom)
                    start.linkTo(fromTxt.start)
                }
        )

        // TO
        Text(
            text = item.To,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toTxt) {
                    top.linkTo(timeTxt.bottom, margin = 8.dp)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = item.ToShort,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toShortTxt) {
                    top.linkTo(toTxt.bottom)
                    end.linkTo(toTxt.end)
                }
        )

        // AIRPLANE ICON
        Image(
            painter = painterResource(R.drawable.line_airple_blue),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(airplaneIcon) {
                    top.linkTo(fromShortTxt.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // DASH LINE
        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(dashLine) {
                    top.linkTo(airplaneIcon.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // SEAT ICON (canto inferior esquerdo)
        Image(
            painter = painterResource(R.drawable.seat_black_ic),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(seatIcon) {
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        // CLASS TEXT (ao lado do ícone do assento)
        Text(
            text = item.ClassSeat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .constrainAs(classTxt) {
                    start.linkTo(seatIcon.end, margin = 8.dp)
                    top.linkTo(seatIcon.top)
                    bottom.linkTo(seatIcon.bottom)
                }
        )

        // PRICE (canto inferior direito)
        Text(
            text = "$${String.format("%.2f", item.Price)}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = colorResource(R.color.orange),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(priceTxt) {
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
    }
}
