package com.detrua.termin_suter_training

import android.os.Bundle
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        java.util.Calendar.getInstance()

        val mWebView = findViewById<View>(R.id.webViewSuter) as WebView
        mWebView.loadUrl("https://www.terminland.de/suter-training/")

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()

        mWebView.canGoBack()
        mWebView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK

                    && event.action == MotionEvent.ACTION_UP
                    && mWebView.canGoBack()){
                    mWebView.goBack()
                    return@OnKeyListener true
            }
            false
        })
    }

    fun dialog_website(view: View) {

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("Info")
        builder.setMessage("Website im Browser öffnen ?")
        builder.setIcon(R.drawable.info_dialog)

        builder.setPositiveButton("Ok" , DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            val url = "https://www.suter-training.ch/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
        })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }

    fun dialog_email(view: View) {

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
        builder.setMessage("E-Mail senden ?")
        builder.setIcon(R.drawable.info_dialog)

        builder.setPositiveButton("Ok" , DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("markus@suter-training.ch"))

            try{

                startActivity(Intent.createChooser(mIntent, "Email Client auswählen..."))

            }
            catch (e: Exception){

                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

            }

        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }

    fun dialog_call(view: View) {

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
        builder.setMessage("Markus Suter anrufen ?")
        builder.setIcon(R.drawable.info_dialog)

        builder.setPositiveButton("Ok" , DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "+41 79 235 77 52")
            startActivity(dialIntent)

        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }


}


