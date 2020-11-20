package com.johnny.publisher.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.johnny.publisher.R
import com.johnny.publisher.databinding.FragmentArticleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this).get(ArticleViewModel ::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleBinding.inflate(
            inflater, container,
            false)

        val adapter = ArticleAdapter()
        binding.recyclerviewArticle.adapter = adapter

        viewModel.allArticle.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        binding.Button.setOnClickListener {
            findNavController().navigate(R.id.action_articleFragment_to_postDialog)
        }

        return binding.root
    }
}
