package com.sergiocasero.kmpauth.model

sealed class User(val uuid: String) {
    class Anonymous(uuid: String) : User(uuid)
    class Email(
        uuid: String,
        email: String,
        displayName: String?
    ) : User(uuid)
}
