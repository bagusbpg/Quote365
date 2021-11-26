package com.example.quote365

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quote365.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding
    val quote = QuoteData.data
    val size = quote.size

    private fun showQuote() {
        val index = Random.nextInt(0, size)
        bindingMain.viewQuote.text = quote[index][0]
        bindingMain.viewPerson.text = quote[index][1]
    }

    private fun shareQuote() {
        val toShare = bindingMain.viewQuote.text.toString() + " - " + bindingMain.viewPerson.text.toString()
        val shareIntent = Intent()

        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, toShare)
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        showQuote()
        bindingMain.refreshButton.setOnClickListener { showQuote() }
        bindingMain.shareButton.setOnClickListener { shareQuote() }
    }
}