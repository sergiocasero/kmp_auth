package presentation.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sergiocasero.kmpauth.model.Error
import com.sergiocasero.kmpauth.model.User
import com.sergiocasero.kmpauthsample.CommonSamplePresenter
import com.sergiocasero.kmpauthsample.CommonSampleView
import com.sergiocasero.kmpauthsampleandroid.R
import kotlinx.android.synthetic.main.activity_sample.*

class SplashActivity : AppCompatActivity(), CommonSampleView {

    private val presenter by lazy { CommonSamplePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        button.setOnClickListener { presenter.onLoginAnonymousClick() }
    }

    override fun showUser(user: User) {
        message.text = "User received: ${user.uuid}"
    }

    override fun showError(error: Error) {
        message.text = "Error: $error"
    }
}
