package com.example.youtubetest.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubetest.databinding.FragmentHomeBinding
import com.example.youtubetest.utils.closeKeyBord

class HomeFragment : Fragment(),TextWatcher  {

    private var _binding: FragmentHomeBinding? = null
    val homeViewModel: HomeViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        observePlayList()

    }

    private fun observePlayList() {
        binding.progressBar.visibility = View.VISIBLE
        homeViewModel.liveDataUsers.observe(viewLifecycleOwner, Observer { usersListWith ->
            binding.progressBar.visibility = View.GONE
            //TODO PAGEING
            if( usersListWith.isNullOrEmpty()){
                binding.noResultsR.visibility=View.VISIBLE
                binding.myRecyclerView.visibility=View.GONE
            }
            else {
                binding.noResultsR.visibility=View.GONE
                binding.myRecyclerView.visibility=View.VISIBLE
                ?.setUserList(
                    usersListWith,
                    userSearchViewModel.isUpdate,
                    userSearchViewModel.searchStr
                )
                userAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun startSearch(s: String) {
        var sortList=trialDatsViewModel.playList.filter { it.courtDescription.lowercase().contains(s)}
        if(sortList.isNullOrEmpty()){
            binding.noResultsR.visibility=View.VISIBLE
            binding.myRecyclerView.visibility=View.GONE
        }
        else{
            binding.noResultsR.visibility=View.GONE
            binding.myRecyclerView.visibility=View.VISIBLE
            searchAdapter.list=sortList
        }
        searchAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerview() {
        _binding?.myRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        var searchAdapter =
            SearchAdapter(
                requireContext(),
                homeViewModel.courtsList
            ) {
                onItemClicked(it)
            }
        _binding?.myRecyclerView?.adapter = searchAdapter
    }

    private fun onItemClicked(it: Any) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
        closeKeyBord(requireActivity())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        startSearch(s.toString())
        binding.searchLayout.deleteSearch.visibility = if (s.isNullOrEmpty()) View.VISIBLE else View.GONE
    }



    override fun afterTextChanged(s: Editable?) {
    }
}