package com.dhis.store.presentation.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.dhis.store.R
import com.dhis.store.databinding.ActivityDetailsBinding
import com.dhis.store.di.InjectorUtils
import com.dhis.store.presentation.extension.visible

class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels {
        InjectorUtils.provideDetailsViewModelFactory(
            this,
            intent.getIntExtra(KEY_APP_ID, -1)
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
        val adapter = CommentsAdapter()

        binding.apply {
            setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener { onBackPressed() }
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.app.observe(this@DetailsActivity) {
                supportActionBar?.title = it.title
                app = it
            }

            commentList.adapter = adapter

            viewModel.comments.observe(this@DetailsActivity) { apps ->
                commentsTitleView.visible(apps.isNotEmpty())
                commentList.visible(apps.isNotEmpty())
                adapter.submitList(apps)
            }
        }
    }

    companion object {
        private const val KEY_APP_ID = "KEY_APP_ID"

        fun open(context: Context, appId: Int) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_APP_ID, appId)
            context.startActivity(intent)
        }
    }
}
