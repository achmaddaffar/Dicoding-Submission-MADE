@file:Suppress("DEPRECATION")

package com.daffa.nasainsight.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.daffa.core.domain.model.Apod
import com.daffa.nasainsight.R
import com.daffa.nasainsight.databinding.ActivityDetailApodBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailApodActivity : AppCompatActivity() {

    private val detailApodViewModel: DetailApodViewModel by viewModel()
    private lateinit var binding: ActivityDetailApodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailApodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail"

        val detailApod = intent.getParcelableExtra<Apod>(EXTRA_DATA)
        showDetailApod(detailApod)
    }

    private fun showDetailApod(apod: Apod?) {
        apod?.let {
            binding.apply {
                Glide.with(this@DetailApodActivity)
                    .load(it.url)
                    .into(ivApod)

                tvApodTitle.text = it.title
                tvApodDate.text = it.date
                it.copyright?.let {
                    tvApodCopyright.text = StringBuilder("by ").append(it)
                }
                tvApodDescription.text = it.explanation

                var statusFavorite = it.isFavorite
                setStatusFavorite(statusFavorite)
                binding.fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailApodViewModel.setFavoriteApod(apod, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite)
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        else
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
    }

    companion object {
        const val EXTRA_DATA = "apod_extra_data"
    }
}