package io.realm.kotlin.demo.util

fun isValidName(name: String): Boolean {
    return name.matches(Regex("^[A-Za-z'-]{2,20}\$"))
}

fun isValidBirthDate(birthDate: String): Boolean {
    //the below regular expression may not be ideal, but it appears to work and that's what matters for now
    val birthDateRegex = """^(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/(19|20)[0-9][0-9]"""
    return birthDate.matches(Regex(birthDateRegex))
}

//use this to validate all inputted client info at once when doing the final button click needed to "create" a new client
fun isValidClientInfo(firstName: String, lastName: String, birthDate: String): Boolean {
    //Add validation function as needed below
    return  isValidName(firstName) &&
            isValidName(lastName) &&
            isValidBirthDate(birthDate)
}

//fun updateClientHealthIssues(client: Client, healthIssues: List<String>) {
//    client.setInitialHealthIssues(healthIssues.toMutableList())
//}
