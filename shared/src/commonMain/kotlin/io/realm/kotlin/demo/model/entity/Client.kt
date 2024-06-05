package io.realm.kotlin.demo.model.entity

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class Client(
    @PrimaryKey
    var _id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: String = "",
    var initialPain: String = "",
    var initialHealthIssues: RealmList<String> = realmListOf()
    //TODO: add a member for 'Notes'? (something for when memos and whatnot are added to a specific client
    //Below - something for recording time of creation??
    //var timestamp: RealmInstant = RealmInstant.now()
) : RealmObject {

    /*NOTE: If you run into any issues with the RealmClass here, you might need to move the properties down to here*/

    //****IMPORTANT**** The need for an 'empty constructor' with RealmObjects
    //https://www.mongodb.com/docs/atlas/device-sdks/sdk/kotlin/realm-database/schemas/define-realm-object-model/#std-label-kotlin-define-object-model

    //constructor below (as mentioned above):
    @Suppress("unused")
    constructor(): this("", "", "", "", "", realmListOf())

    //***NOTE: It turns out basic getters/setters have no need to be explicitly stated in Kotlin
    //Modifying get()/set():
    //https://www.baeldung.com/kotlin/getters-setters

    fun getInitialHealthIssues(): String {
        return initialHealthIssues.joinToString(separator = ", ")
    }

}