package com.johnny.publisher.post

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.johnny.publisher.R
import com.johnny.publisher.data.Article
import com.johnny.publisher.databinding.DialogPostBinding

class PostDialog : DialogFragment() {


    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(this).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogPostBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonPost.setOnClickListener {
            if (binding.editTitle.text.toString().isNullOrBlank()) {
                val toast = Toast.makeText(context, "請輸入標題", Toast.LENGTH_SHORT)
                toast.show()
            } else if (binding.editTag.text.toString().isNullOrBlank()) {
                val toast = Toast.makeText(context, "請輸入tag", Toast.LENGTH_SHORT)
                toast.show()
            } else if (binding.editContent.text.toString().isNullOrBlank()) {
                val toast = Toast.makeText(context, "請輸入內容", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                viewModel.addData(
                    title = binding.editTitle.text.toString(),
                    tag = binding.editTag.text.toString(),
                    content = binding.editContent.text.toString()
                )
                val toast = Toast.makeText(context, "發文成功囉", Toast.LENGTH_SHORT)
                toast.show()

                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    findNavController().navigate(R.id.action_postDialog_to_articleFragment)
                }, 1000)
            }
        }

        return binding.root
    }
}
