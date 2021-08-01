@file:Suppress("DEPRECATION")

package com.mobileexercise.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobileexercise.R
import com.mobileexercise.api.ApiCall
import com.mobileexercise.api.ApiConsent
import com.mobileexercise.api.KEY_PASSWORD
import com.mobileexercise.api.KEY_USERNAME
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.lang.ref.WeakReference


class LoginActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    @Suppress("DEPRECATION")
    private fun initView() {
        btnLogin.setOnClickListener {
            val userName: String = etUserName.text.toString()
            val password: String = etPassword.text.toString()
            val loginJson = JSONObject()
            if (userName.isNotEmpty() && password.isNotEmpty()){
                if (userName == "morty" && password == "smith") {
                    loginJson.put(KEY_USERNAME, "morty")
                    loginJson.put(KEY_PASSWORD, "smith")
                    LoginTask(this).execute(loginJson.toString())
                }else{
                    Toast.makeText(this,"Please enter valid username and password.",
                        Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please enter username and password.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    @Suppress("DEPRECATION")
    inner class LoginTask(context: Context) : AsyncTask<String?, String?, String?>() {
        private var dialog: ProgressDialog? = null
        private var contextRef: WeakReference<Context> = WeakReference(context)

        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog(contextRef.get())
            dialog!!.setMessage("Loading...")
            dialog!!.setCancelable(false)
            dialog!!.show()
        }

        override fun doInBackground(vararg params: String?): String {
            Log.d("TAG", "doInBackground: ${params[0]}")
            var responseString = ""
            try {
                responseString = ApiCall().postRequest(ApiConsent().API_LOGIN,params[0].toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return responseString
        }

        override fun onPostExecute(responseString: String?) {
            super.onPostExecute(responseString)
            dialog!!.dismiss()
            //set result in textView
            if (responseString != null) {
                Log.d("TAG", "onPostExecute: ")
               navigateDashboardScreen()
            }
        }
    }
     fun navigateDashboardScreen() {
        DashboardActivity.launch(this)
         finish()
    }
}