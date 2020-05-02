package com.sergiocasero.kmpauth

import com.sergiocasero.kmpauth.model.Either
import com.sergiocasero.kmpauth.model.Error
import com.sergiocasero.kmpauth.model.Success
import com.sergiocasero.kmpauth.model.User

expect operator fun KmpAuth.Companion.invoke(): KmpAuth

expect class KmpAuth {
    companion object;

    suspend fun signInWithUserAndPassword(email: String, password: String): Either<Error, User>
    suspend fun signInAnonymously(): Either<Error, User>
    suspend fun getUser(): Either<Error, User>
    suspend fun signOut(): Either<Error, Success>
}
