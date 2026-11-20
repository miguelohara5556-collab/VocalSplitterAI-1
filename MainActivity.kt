package com.vocalsplitter.ai

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.graphics.Color

class MainActivity : Activity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(18, 18, 28))
        }

        val title = TextView(this).apply {
            text = "VOCAL SPLITTER AI"
            textSize = 28f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Separe voz e instrumental das suas músicas"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val chooseButton = Button(this).apply {
            text = "ESCOLHER MÚSICA"
            setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "audio/*"
                }
                startActivityForResult(intent, 100)
            }
        }

        val separateButton = Button(this).apply {
            text = "SEPARAR VOZ"
            setOnClickListener {
                statusText.text =
                    "Motor de separação será ativado na próxima etapa."
            }
        }

        statusText = TextView(this).apply {
            text = "Nenhuma música selecionada"
            textSize = 15f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(chooseButton)
        layout.addView(separateButton)
        layout.addView(statusText)

        setContentView(layout)
    }

    @Deprecated("Deprecated in Android")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            data?.data?.let {
                statusText.text = "Música selecionada com sucesso!"
            }
        }
    }
}
