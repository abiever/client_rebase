package io.realm.kotlin.demo.composables



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckableRow(healthIssues: List<String>, checkedHealthIssues: MutableList<String>) {
    MaterialTheme {
        Column {
            healthIssues.forEach { healthIssue ->
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {
                            if (checkedHealthIssues.contains(healthIssue)) {
                                checkedHealthIssues.remove(healthIssue) // Unselect the health issue
                            } else {
                                checkedHealthIssues.add(healthIssue) // Select the health issue
                            }
                        })
                ) {
                    Text(healthIssue, Modifier.weight(1f))
                    Checkbox(
                        checked = checkedHealthIssues.contains(healthIssue),
                        onCheckedChange = {
                            if (it) {
                                checkedHealthIssues.add(healthIssue) // Add the health issue if checked
                            } else {
                                checkedHealthIssues.remove(healthIssue) // Remove the health issue if unchecked
                            }
                        }
                    )
                }
            }
        }
    }
}
