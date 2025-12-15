package com.pedrovenda.tiicketbookingapp.Activities.SearchResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pedrovenda.tiicketbookingapp.Activities.Domain.FlightModel
import com.pedrovenda.tiicketbookingapp.R
import com.pedrovenda.tiicketbookingapp.ViewModel.MainViewModel

@Composable
fun ItemListScreen(
    from: String,
    to: String,
    viewModel: MainViewModel,
    onBackClick: () -> Unit
) {
    val items by viewModel.loadFiltered(from, to).observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    // sempre que muda o filtro, volta a "carregar"
    LaunchedEffect(from, to) {
        isLoading = true
        viewModel.loadFiltered(from, to) // (não é obrigatório, mas mantém o teu padrão)
    }

    // quando o LiveData atualiza (mesmo vazio), termina loading
    LaunchedEffect(items) {
        isLoading = false
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkPurple2))
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {

        val (backBtn, headerTitle, worldImg, content) = createRefs()

        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .clickable { onBackClick() }
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Search Result",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(headerTitle) {
                    start.linkTo(backBtn.end, margin = 8.dp)
                    top.linkTo(backBtn.top)
                    bottom.linkTo(backBtn.bottom)
                }
        )

        Image(
            painter = painterResource(R.drawable.world),
            contentDescription = null,
            modifier = Modifier.constrainAs(worldImg) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        // CONTEÚDO (loading / empty / list)
        Box(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(headerTitle.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                height = androidx.constraintlayout.compose.Dimension.fillToConstraints
            }
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                items.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sem resultados para $from → $to",
                            color = Color.White
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        itemsIndexed(items) { index, item ->
                            FlightItem(item = item, index = index)
                        }
                    }
                }
            }
        }
    }
}
