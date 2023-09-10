package com.daffa.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.core.domain.model.Apod
import com.daffa.core.ui.ApodAdapter
import com.daffa.favorite.databinding.FragmentFavoriteBinding
import com.daffa.favorite.di.favoriteModule
import com.daffa.nasainsight.ui.detail.DetailApodActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val apodAdapter = ApodAdapter()
            var apodFavoriteList: List<Apod>
            favoriteViewModel.apodFavorite.observe(viewLifecycleOwner) {
                apodFavoriteList = it
                showEmptyText(it.isEmpty())
                apodAdapter.setData(apodFavoriteList)
                apodAdapter.onItemClick = { selectedData ->
                    val intent = Intent(context, DetailApodActivity::class.java)
                    intent.putExtra(DetailApodActivity.EXTRA_DATA, selectedData)
                    startActivity(intent)
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

    private fun showEmptyText(isEmpty: Boolean) {
        binding.tvEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }
}