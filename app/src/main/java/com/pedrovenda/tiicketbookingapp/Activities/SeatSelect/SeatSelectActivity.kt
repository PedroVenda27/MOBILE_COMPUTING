package com.pedrovenda.tiicketbookingapp.Activities.SeatSelect

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pedrovenda.tiicketbookingapp.Activities.Domain.FlightModel
import com.pedrovenda.tiicketbookingapp.Activities.Splash.StatusTopBarColor
import com.pedrovenda.tiicketbookingapp.R

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var flight: FlightModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        flight = intent.getSerializableExtra("flight") as FlightModel
        setContent{
            StatusTopBarColor()
            SeatListScreen(
                flight = flight,
                onBackClick = {
                    finish() },
                onConfirm = {

                }
            )
        }
    }
}
