package io.realm.kotlin.demo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.realm.kotlin.demo.model.entity.Client

//This is going to be the main spot where clients are tracked/created/etc.

data class HomeScreen(val clientsList: MutableList<Client>) : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (clientsList.isNotEmpty()) {
                for (client in clientsList) {
                    // Use a composable function to create the button
                    //ShowMoreButton(client = client)
                    Button(
                        onClick = {
                            navigator.push(ClientProfileScreen(client, clientsList))
                        }
                    ) {
                        Text(client.firstName + " " + client.lastName)
                    }
                }
                //TODO: The below may be able to be turned into a Composable for reusability/editability
                Button(
                    onClick = { navigator.push(NewClientFormScreen(clientsList))}
                ) {
                    Text("Add New Client")
                }
            }
            if (clientsList.isEmpty()) {
                Text("You currently have no clients.")
                Button(
                    onClick = { navigator.push(NewClientFormScreen(clientsList))}
                ) {
                    Text("Add New Client")
                }
            }
        }
    }

}