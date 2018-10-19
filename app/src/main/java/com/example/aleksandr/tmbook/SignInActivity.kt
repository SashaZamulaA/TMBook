package com.example.aleksandr.tmbook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_sing_in.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import com.firebase.ui.auth.util.ExtraConstants
import com.firebase.ui.auth.data.model.FlowParameters



class SignInActivity : AppCompatActivity() {

    private val RC_SING_IN = 1

    private val singInProviders = listOf(AuthUI.IdpConfig.EmailBuilder()
            .setAllowNewAccounts(true)
            .setRequireName(true)
            .build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        account_sign_in.setOnClickListener {
            val intent = AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(singInProviders)
                    .setLogo(R.drawable.ic_angel_clipart_christmas_1)
                    .build()
            startActivityForResult(intent, RC_SING_IN)
        }

    }

    fun fromIntent(intent: Intent): FlowParameters {
        //this is required to fix #1416 - ClassNotFound for FlowParameters
        intent.setExtrasClassLoader(AuthUI::class.java.classLoader)
        return intent.getParcelableExtra(ExtraConstants.FLOW_PARAMS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SING_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (requestCode == Activity.RESULT_OK) {
                val progressDialog = indeterminateProgressDialog("Setting up your account")
//TODO fireBase
                startActivity(intentFor<MainActivity>().newTask().clearTask())
                progressDialog.dismiss()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (response == null) return

                when (response.error?.errorCode){
                    ErrorCodes.NO_NETWORK ->
                        longSnackbar(constraint_layout, "No network")
                    ErrorCodes.UNKNOWN_ERROR -> longSnackbar(constraint_layout, "Unknown error")
                }

            }
        }

    }
}
