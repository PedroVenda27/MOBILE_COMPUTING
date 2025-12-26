# ✈️ TicketBookingApp – Flight Booking Application

## 📱 Project Overview
This project consists of an Android mobile application for flight browsing and seat reservation, developed using **Kotlin** and **Jetpack Compose**, with **Firebase Realtime Database** used for data persistence.

The application allows users to:
- View available flights
- Check flight details
- Select seats visually
- Simulate seat reservation with real-time updates

This project was developed for academic purposes and follows a tutorial-based implementation, adapted and extended to meet project requirements.

---

## 🎯 Objectives
- Develop a modern Android UI using Jetpack Compose
- Implement reactive UI with state management
- Integrate Firebase Realtime Database
- Model and manage seat availability dynamically
- Apply clean project structure and modular design

---

## 🛠️ Technologies Used
- **Kotlin**
- **Jetpack Compose**
- **Firebase Realtime Database**
- **Android Studio**
- **Material Design / Material3**
- **LazyColumn & LazyVerticalGrid**

---

## 🗂️ Project Structure~

``` 
com.pedrovenda.ticketbooking
│
├── Activities
│   ├── Dashboard
│   │   ├── DashboardActivity.kt
│   │   ├── DataPickerScreen.kt
│   │   ├── DropDownList.kt
│   │   ├── PassengerCounter.kt
│   │   ├── TopBar.kt
│   │   └── MyBottomBar.kt
│   │
│   ├── SearchResult
│   │   ├── SearchResultActivity.kt
│   │   ├── ItemListScreen.kt
│   │   └── FlightItem.kt
│   │
│   ├── TicketDetail
│   │   ├── TicketDetailActivity.kt
│   │   ├── TicketDetailScreen.kt
│   │   └── TicketDetailContent.kt
│   │
│   ├── SeatSelect
│   │   ├── SeatSelectActivity.kt
│   │   ├── SeatListScreen.kt
│   │   ├── SeatItem.kt
│   │   ├── LegendItem.kt
│   │   ├── BottomSection.kt
│   │   ├── TopSection.kt
│   │   └── TicketHeader.kt
│   │
│   └── Splash
│       ├── SplashActivity.kt
│       └── GradientButton.kt
│
├── Domain
│   ├── FlightModel.kt
│   └── LocationModel.kt
│
├── Repository
│   └── MainRepository.kt
│
├── ViewModel
│   └── MainViewModel.kt
│
├── ui.theme
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
└── MainActivity.kt

```


## 🧩 Data Model

The application data is stored in **Firebase Realtime Database** and defined in the JSON file available in this repository:

📄 **Database file:**  
[`tiicketbookingapp.json`](./tiicketbookingapp.json)

---

### ✈️ Flights

The `Flights` node stores all flight-related information used throughout the application, including search results, flight details and seat availability.

Each flight contains the following fields:
- `airlineName` – airline name  
- `airlineLogo` – airline logo URL  
- `from` / `fromShort` – origin (full name and code)  
- `to` / `toShort` – destination (full name and code)  
- `date` – flight date  
- `time` – departure time  
- `arriveTime` – flight duration  
- `classSeat` – seat class (Economy / Business)  
- `price` – ticket price  
- `numberSeat` – total number of seats  
- `reservedSeats` – list of reserved seats stored as a string  

### 📍 Locations

The Locations node stores the list of available cities used in the flight search and filtering process.

Each location contains the following fields:

- `Id` – unique numeric identifier
- `Name` – full name of the location


## 🪑 Seat Selection System

The seat selection feature is implemented using a `LazyVerticalGrid`, where seats are dynamically generated based on the selected flight.

Each seat can have one of the following states:
- **AVAILABLE** – seat is free and can be selected  
- **SELECTED** – seat selected by the user  
- **UNAVAILABLE** – seat already reserved  
- **EMPTY** – represents aisles or empty spaces in the aircraft layout  

Reserved seats retrieved from Firebase are automatically marked as **UNAVAILABLE**, preventing user interaction.

---

## 🔥 Firebase Integration

Firebase Realtime Database is responsible for:
- Providing flight data
- Managing reserved seats
- Synchronizing UI state with database updates

Data access is handled through the `MainRepository`, and the retrieved data is exposed to the UI layer via `MainViewModel`, following the MVVM architecture pattern.

---

## 🧪 Testing

Manual testing was performed to validate:
- Correct UI rendering
- Navigation between application screens
- Seat availability and selection logic
- Firebase data persistence after application restart

---

## 👤 Author  

Repository developed by **[Pedro Venda](https://github.com/PedroVenda27)**  
