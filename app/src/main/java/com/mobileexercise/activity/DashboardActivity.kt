package com.mobileexercise.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileexercise.R
import com.mobileexercise.adapter.CustomAdapter
import com.mobileexercise.api.*
import com.mobileexercise.model.AccountDetails
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONArray
import java.lang.ref.WeakReference


class DashboardActivity : AppCompatActivity() {
    var accountList:ArrayList<AccountDetails> = ArrayList();
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, DashboardActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initView()
    }

    @Suppress("DEPRECATION")
    private fun initView() {
        ivQuickSettings.setOnClickListener { NavigateMenuActivity.launch(this) }
        GetTask(this).execute()
    }
    @Suppress("DEPRECATION")
    inner class GetTask(context: Context) : AsyncTask<String?, String?, String?>() {
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
            var responseString = ""
            try {
                responseString = ApiCall().getRequest(ApiConsent().API_ACCOUNT).toString()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return responseString
        }

        override fun onPostExecute(response: String?) {
            super.onPostExecute(response)
            dialog!!.dismiss()
            Log.d("TAG", "parseResponse: $response")
            if (response!!.isNotEmpty()) {
                parseResponse(response)
            }else{
                Log.d("TAG", "onPostExecute: NULL Response")
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun parseResponse(responseString: String) {
        Log.d("TAG", "parseResponse: $responseString")
        val accountResponse = JSONArray(responseString)
        var id = ""
        var name = ""
        var balance = ""
        for (i in 0 until accountResponse.length()) {
            val jsonObject = accountResponse.getJSONObject(i)
            id = jsonObject.optString(KEY_ID)
            name = jsonObject.optString(KEY_NAME)
            balance = jsonObject.optString(KEY_BALANCE)
            accountList.add(AccountDetails(id,name,balance))
        }
        val adapter = CustomAdapter(accountList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
}