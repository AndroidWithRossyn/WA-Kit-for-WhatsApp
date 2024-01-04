package com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.ui.chat.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.FragmentChatDetailBinding
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.ui.main.DeletedMainActivity
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.ui.chat.ChatViewModel
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.utility.hide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailFragment : Fragment() {

    private val adapter = ChatDetailAdapter()
    private val viewModel: ChatViewModel by viewModels()

    private var _binding: FragmentChatDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchChatsByUser()
    }

    private fun fetchChatsByUser() {
        val user = arguments?.getString("user") ?: ""
        //Display user in toolbar title
        (activity as DeletedMainActivity).supportActionBar?.title = user

        viewModel.getChatByUser.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recyclerView.adapter = adapter
            binding.loading.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}