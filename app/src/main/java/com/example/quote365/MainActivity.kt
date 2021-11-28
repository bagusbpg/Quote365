package com.example.quote365

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quote365.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding
    private val quote = QuoteData.data
    private val size = quote.size

    companion object {
        private const val STATE_RESULT1 = "state_result"
        private const val STATE_RESULT2 = "state_result"
    }

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

        if (savedInstanceState != null) {
            val result1 = savedInstanceState.getString(STATE_RESULT1)
            val result2 = savedInstanceState.getString(STATE_RESULT2)
            bindingMain.viewQuote.text = result1
            bindingMain.viewPerson.text = result2
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT1, bindingMain.viewQuote.text.toString())
        outState.putString(STATE_RESULT2, bindingMain.viewPerson.text.toString())
    }
}