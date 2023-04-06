package com.buanangelica.shamelesssselfpromoapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

    private lateinit var message : Message
    private lateinit var messagePreviewText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        displayMessage()
        setupButton()
    }

    private fun displayMessage() {
         message = intent.getSerializableExtra("Message") as Message
         messagePreviewText = """
             Hi ${message.contactName},
             
             My name is ${message.myDisplayName} and I am ${message.getFullJobDescription()}.
             
             I have a portfolio of apps to demonstrate my technical skills that I can show on request.
             
             I am able to start a new position ${message.getAvailability()}.
             
             Please get in touch if you have any suitable roles for me.
             
             Thanks and best regards.
         """.trimIndent()
        text_view_message.text = messagePreviewText
    }
    private fun setupButton(){
    button_send_message.setOnClickListener {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto: ${message.contactNumber}")  // This ensures only SMS apps respond
            putExtra("sms_body", messagePreviewText)
        }
        startActivity(intent)
    }
    }
}