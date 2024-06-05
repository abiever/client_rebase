package io.realm.kotlin.demo.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.realm.kotlin.demo.model.entity.Client
import io.realm.kotlin.demo.composables.CheckableRow
import io.realm.kotlin.demo.util.isValidBirthDate
import io.realm.kotlin.demo.util.isValidClientInfo
import io.realm.kotlin.demo.util.isValidName
import io.realm.kotlin.ext.toRealmList

data class NewClientFormScreen(val clientsList: MutableList<Client>) : Screen { //Need ComponentActivity() here??

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val healthIssues = listOf("Diabetes", "Hypertension", "Obesity")
        val checkedHealthIssues = remember { mutableStateListOf<String>() }

        var firstName by remember { mutableStateOf(("")) }
        TextField(
            value = firstName,
            label = { Text("Enter First Name:") },
            singleLine = true,
            onValueChange = { firstName = it },
            isError = firstName.isNotEmpty() && !isValidName(firstName),
            supportingText = {
                if (firstName.isNotEmpty() && !isValidName(firstName)) {
                    Text("Please enter a valid first name")
                }
            },
            modifier = Modifier.padding(20.dp)
        )

        var lastName by remember { mutableStateOf(("")) }
        TextField(
            value = lastName,
            label = { Text("Enter Last Name:") },
            singleLine = true,
            onValueChange = { lastName = it },
            isError = lastName.isNotEmpty() && !isValidName(lastName),
            supportingText = {
                if (lastName.isNotEmpty() && !isValidName(lastName)) {
                    Text("Please enter a valid last name")
                }
            },
            modifier = Modifier.padding(20.dp)
        )

        var birthDate by remember { mutableStateOf(("")) }
        TextField(
            value = birthDate,
            label = { Text("Date of Birth") },
            placeholder = { Text("MM/DD/YYYY") },
            singleLine = true,
            onValueChange = { birthDate = it },
            isError = birthDate.isNotEmpty() && !isValidBirthDate(birthDate),
            supportingText = {
                if (birthDate.isNotEmpty() && !isValidBirthDate(birthDate)) {
                    Text("Please enter a valid birth date")
                }
            },
            modifier = Modifier.padding(20.dp)
        )

        Text("How much did pain interfere with your enjoyment of life?")
        val painOptions = listOf("Not at all", "A little bit", "Somewhat", "Quite a bit", "Very much")
        val (selectedPain, onOptionSelected) = remember {
            mutableStateOf(
                painOptions[1]
            )
        }
        Row {
            painOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == selectedPain),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(vertical = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedPain),
                        onClick = { onOptionSelected(text) },
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 8.dp) // Adjust padding as needed
                    )
                }
            }
        }

//TODO: Add modifier to text to be "heading" size and edit options for health issues options
        Text("Any general health issues?")

        CheckableRow(healthIssues = healthIssues, checkedHealthIssues = checkedHealthIssues)

        //TODO: Fix modifier here to help with the alignment
        Button( /*ADD ALIGNMENT MODIFIER HERE??*/
            onClick = {
                //isValidClientInfo() is used here to validate all client info at once
                //prevents button click if data does not pass validation
                if (isValidClientInfo(firstName, lastName, birthDate)) {
                    val client = Client(
                        firstName,
                        lastName,
                        birthDate,
                        selectedPain,
                        checkedHealthIssues.toRealmList()
                    )
                    clientsList.add(client)
                    //The below 'resets' the text fields with empty strings
                    firstName = ""
                    lastName = ""
                    birthDate = ""
                    checkedHealthIssues.clear()
                    navigator.push(HomeScreen(clientsList))
                }
            }) {
            Text("Create Client")
        }
    }

}