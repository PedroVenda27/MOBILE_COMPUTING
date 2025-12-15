package com.pedrovenda.tiicketbookingapp.Activities.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pedrovenda.tiicketbookingapp.R // <-- CORREÇÃO: ADICIONAR ESTA LINHA

@Composable
@Preview
fun TopBar() {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        val (world, name, profile, notification, title) = createRefs()

        Image(
            painter = painterResource(R.drawable.world), // Agora vai funcionar
            contentDescription = null,
            modifier = Modifier
                .clickable { }
                .constrainAs(world) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.profile), // Agora vai funcionar
            contentDescription = null,
            modifier = Modifier.constrainAs(profile) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )

        Image(
            painter = painterResource(R.drawable.bell_icon), // Agora vai funcionar
            contentDescription = null,
            modifier = Modifier.constrainAs(notification) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = "Alex Johnson",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(profile.end)
            }
        )

        Text(
            text = stringResource(R.string.dashboard_title), // Agora vai funcionar
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(profile.bottom)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}
