package com.pustovit.dls2020.presentation.privacypolicy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pustovit.dls2020.databinding.ActivityPPBinding
import com.pustovit.dls2020.presentation.home.HomeActivity

class PPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPPBinding

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, PPActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPPBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            startActivity(HomeActivity.getIntent(this))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(HomeActivity.getIntent(this))
        finish()
    }
}