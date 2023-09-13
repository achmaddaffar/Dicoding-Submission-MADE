package com.daffa.nasainsight.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.core.data.source.Resource
import com.daffa.core.ui.ApodAdapter
import com.daffa.nasainsight.R
import com.daffa.nasainsight.databinding.FragmentHomeBinding
import com.daffa.nasainsight.ui.detail.DetailApodActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val apodAdapter = ApodAdapter()
            apodAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailApodActivity::class.java)
                intent.putExtra(DetailApodActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.apod.observe(viewLifecycleOwner) { apod ->
                if (apod != null) {
                    when(apod) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            apodAdapter.setData(apod.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = apod.message ?: getString(R.string.error_sample_text)
                        }
                    }
                }
            }

            with(binding.rvApod) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = apodAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}