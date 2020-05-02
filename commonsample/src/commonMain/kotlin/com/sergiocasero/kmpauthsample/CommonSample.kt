package com.sergiocasero.kmpauthsample

import com.sergiocasero.kmpauth.KmpAuth
import com.sergiocasero.kmpauth.invoke
import com.sergiocasero.kmpauth.model.Error
import com.sergiocasero.kmpauth.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CommonSamplePresenter(private val view: CommonSampleView) {
    private val auth by lazy { KmpAuth() }

    private val scope = CoroutineScope(Job())

    fun onLoginAnonymousClick() {
        scope.launch(Dispatchers.Main) {
            auth.signInAnonymously()
                .fold(
                    error = { view.showError(it) },
                    success = { view.showUser(it) }
                )
        }
    }
}

interface CommonSampleView {
    fun showUser(user: User)
    fun showError(error: Error)
}
