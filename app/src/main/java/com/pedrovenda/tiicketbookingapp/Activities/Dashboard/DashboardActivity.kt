package com.pedrovenda.tiicketbookingapp.Activities.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.pedrovenda.tiicketbookingapp.Activities.Domain.LocationModel
import com.pedrovenda.tiicketbookingapp.Activities.SearchResult.SearchResultActivity
import com.pedrovenda.tiicketbookingapp.Activities.Splash.GradientButton
import com.pedrovenda.tiicketbookingapp.Activities.Splash.StatusTopBarColor
import com.pedrovenda.tiicketbookingapp.R
import com.pedrovenda.tiicketbookingapp.ViewModel.MainViewModel

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
@Preview
fun MainScreen() {

    var locations by remember { mutableStateOf<List<LocationModel>>(emptyList()) }
    val viewModel = MainViewModel()

    var showLocationLoading by remember { mutableStateOf(true) }
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var classes by remember { mutableStateOf("") }
    var adultPassgener by remember { mutableStateOf("") }
    var childPassgener by remember { mutableStateOf("") }
    val context = LocalContext.current




    StatusTopBarColor()

    LaunchedEffect(Unit) {
        viewModel.loadLocations().observeForever { result ->
            locations = result
            showLocationLoading = false
        }
    }

    Scaffold(
        bottomBar = { MyBottomBar() }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.darkPurple2))
                .padding(paddingValues)
        ) {

            item { TopBar() }

            item {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    //from Section
                    YellowTitle(text = "From")
                    val locationNames: List<String> = locations.map { it.Name }

                    DropDownList(
                        items = locationNames,
                        hint = "Select origin",
                        showLocationLoading = showLocationLoading,
                        loadingIcon = painterResource(id = R.drawable.from_ic)
                    ) { selectedItem ->
                        from = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    //to Section
                    YellowTitle(text = "From")
                    DropDownList(
                        items = locationNames,
                        hint = "Select Destination",
                        showLocationLoading = showLocationLoading,
                        loadingIcon = painterResource(id = R.drawable.from_ic)
                    ) { selectedItem ->
                        to = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    //passanger Counter
                    Row (modifier = Modifier.fillMaxWidth()){
                        PassengerCounter(
                            title = "Adult",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {adultPassgener = it}
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PassengerCounter(
                            title = "Child",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {childPassgener = it}
                        )
                    }

                    //Calendar Picker
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        YellowTitle(text = "Departure Date", Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(16.dp))
                        YellowTitle(text = "Return Date", Modifier.weight(1f))

                    }
                    DatePickerScreen(Modifier.weight(1f))



                    Spacer(modifier = Modifier.height(16.dp))

                    //classes Section
                    YellowTitle(text = "class")
                    val classItems= listOf("Business Class", "First Class", "Economy Class")
                    DropDownList(
                        items = classItems,
                        hint = "Select Class",
                        showLocationLoading = showLocationLoading,
                        loadingIcon = painterResource(id = R.drawable.seat_black_ic)
                    ) { selectedItem ->
                        to = selectedItem
                    }

                    //Search Button
                    Spacer(modifier = Modifier.height(16.dp))

                    GradientButton(
                        onClick = {
                            val intent = Intent(context, SearchResultActivity::class.java).apply {
                                putExtra("from", from)
                                putExtra("to", to)
                                putExtra("numPassenger", adultPassgener + childPassgener)
                            }
                            startActivity(context, intent, null)
                        },
                        text = "Search",
                    )


                }
            }
        }
    }
}

@Composable
fun YellowTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.orange),
        modifier = modifier
    )
}
