package com.sergiocasero.kmpauth

import com.google.firebase.auth.FirebaseAuth
import com.sergiocasero.kmpauth.model.Either
import com.sergiocasero.kmpauth.model.Error
import com.sergiocasero.kmpauth.model.Success
import com.sergiocasero.kmpauth.model.User
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class KmpAuth {

    companion actual object;

    private val auth by lazy { FirebaseAuth.getInstance() }

    actual suspend fun signInWithUserAndPassword(
        email: String,
        password: String
    ): Either<Error, User> {
        return suspendCancellableCoroutine { cont ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener {
                    cont.resume<Either<Error, User>>(
                        Either.Left(
                            Error.Default(
                                it.localizedMessage ?: ""
                            )
                        )
                    )
                }
                .addOnSuccessListener {
                    cont.resume<Either<Error, User>>(
                        Either.Right(
                            User.Email(
                                uuid = it.user?.uid ?: "",
                                displayName = it.user?.displayName ?: "",
                                email = it.user?.email ?: ""
                            )
                        )
                    )
                }
        }

    }

    actual suspend fun signInAnonymously(): Either<Error, User> {
        return suspendCancellableCoroutine { cont ->
            auth.signInAnonymously()
                .addOnFailureListener {
                    cont.resume<Either<Error, User>>(
                        Either.Left(
                            Error.Default(
                                it.localizedMessage ?: ""
                            )
                        )
                    )
                }
                .addOnSuccessListener {
                    cont.resume<Either<Error, User>>(
                        // TODO Solve this
                        Either.Right(User.Anonymous(uuid = it.user?.uid ?: ""))
                    )
                }
        }
    }

    actual suspend fun getUser(): Either<Error, User> {
        return suspendCancellableCoroutine { cont ->
            val currentUser = auth.currentUser
            if (currentUser != null) {
                if (currentUser.isAnonymous) {
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
    }

    actual suspend fun signOut(): Either<Error, Success> {
        return suspendCancellableCoroutine { cont ->
            try {
                auth.signOut()
                cont.resume<Either<Error, Success>>(Either.Right(Success))
            } catch (e: Exception) {
                cont.resume<Either<Error, Success>>(Either.Left(Error.Default(e.message ?: "Default error")))
            }
        }
    }

}

actual operator fun KmpAuth.Companion.invoke(): KmpAuth = KmpAuth()
