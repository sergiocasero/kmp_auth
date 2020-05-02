package com.sergiocasero.kmpauth

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRAuthDataResultCallback
import com.sergiocasero.kmpauth.model.Either
import com.sergiocasero.kmpauth.model.Error
import com.sergiocasero.kmpauth.model.Success
import com.sergiocasero.kmpauth.model.User
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import kotlin.coroutines.resume

actual class KmpAuth {

    actual companion object;

    private val auth by lazy { FIRAuth.auth() }

    actual suspend fun signInWithUserAndPassword(
        email: String,
        password: String
    ): Either<Error, User> {
        return suspendCancellableCoroutine { cont ->
            auth.signInWithEmail(email = email, password = password, completion = object : FIRAuthDataResultCallback {
                override fun invoke(p1: FIRAuthDataResult?, p2: NSError?) {
                    if (p2 != null) {
                        cont.resume<Either<Error, User>>(Either.Left(Error.Default(p2.localizedDescription)))
                    } else {
                        // TODO catch this errors
                        cont.resume<Either<Error, User>>(
                            Either.Right(
                                User.Email(
                                    uuid = p1?.user?.uid ?: "",
                                    displayName = p1?.user?.displayName ?: "",
                                    email = p1?.user?.email ?: ""
                                )
                            )
                        )
                    }
                }
            })
        }
    }

    actual suspend fun signInAnonymously(): Either<Error, User> {
        return suspendCancellableCoroutine { cont ->
            auth.signInAnonymouslyWithCompletion { firAuthDataResult, nsError ->
                if (nsError != null) {
                    cont.resume<Either<Error, User>>(Either.Left(Error.Default(nsError.localizedDescription)))
                } else {
                    cont.resume<Either<Error, User>>(
                        Either.Right(
                            User.Anonymous(
                                uuid = firAuthDataResult?.user?.uid ?: ""
                            )
                        )
                    )
                }
            }
        }
    }

    actual suspend fun getUser(): Either<Error, User> = suspendCancellableCoroutine { cont ->
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if (currentUser.isAnonymous()) {
                cont.resume<Either<Error, User>>(Either.Right(User.Anonymous(currentUser.uid)))
            } else {
                cont.resume<Either<Error, User>>(
                    Either.Right(
                        User.Email(
                            uuid = currentUser.uid,
                            displayName = currentUser.displayName,
                            email = currentUser.email ?: ""
                        )
                    )
                )
            }
        }
    }

    actual suspend fun signOut(): Either<Error, Success> {
        return suspendCancellableCoroutine { cont ->
            try {
                auth.signOut(error = null)
                cont.resume<Either<Error, Success>>(Either.Right(Success))
            } catch (e: Exception) {
                cont.resume<Either<Error, Success>>(Either.Left(Error.Default(e.message ?: "Default error")))
            }
        }
    }
}

actual operator fun KmpAuth.Companion.invoke(): KmpAuth = KmpAuth()
