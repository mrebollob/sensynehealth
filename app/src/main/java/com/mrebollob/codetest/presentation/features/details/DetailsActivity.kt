package com.mrebollob.codetest.presentation.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.mrebollob.codetest.R
import com.mrebollob.codetest.databinding.ActivityDetailsBinding
import com.mrebollob.codetest.di.InjectorUtils

class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels {
        InjectorUtils.provideDetailsViewModelFactory(
            this,
            intent.getIntExtra(KEY_HOSPITAL_ID, -1)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
        binding.lifecycleOwner = this
        initUI(binding)
    }

    private fun initUI(binding: ActivityDetailsBinding) {
        binding.apply {
            setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener { onBackPressed() }
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.app.observe(this@DetailsActivity) {
                supportActionBar?.title = it.organisationName
                hospital = it
            }
        }
    }

    companion object {
        private const val KEY_HOSPITAL_ID = "KEY_HOSPITAL_ID"

        fun open(context: Context, organisationId: Int) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_HOSPITAL_ID, organisationId)
            context.startActivity(intent)
        }
    }
}
