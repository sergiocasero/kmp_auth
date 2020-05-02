package com.sergiocasero.kmpauth

import com.sergiocasero.kmpauth.model.Either
import com.sergiocasero.kmpauth.model.Error
import com.sergiocasero.kmpauth.model.Success
import com.sergiocasero.kmpauth.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class KmpAuthNC(private val scope: CoroutineScope = GlobalScope) {
    private val auth by lazy { KmpAuth() }

    fun signInWithUserAndPassword(email: String, password: String, result: (Either<Error, User>) -> Unit) {
        scope.launch { result(auth.signInWithUserAndPassword(email, password)) }
    }

    fun signInAnonymously(result: (Either<Error, User>) -> Unit) {
        scope.launch { result(auth.signInAnonymously()) }
    }

    fun getUser(result: (Either<Error, User>) -> Unit) {
        scope.launch { result(auth.getUser()) }
    }

    fun signOut(result: (Either<Error, Success>) -> Unit) {
        scope.launch { result(auth.signOut()) }
    }
}
