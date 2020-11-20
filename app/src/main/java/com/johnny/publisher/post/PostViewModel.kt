package com.johnny.publisher.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.johnny.publisher.data.Article
import java.util.*

class PostViewModel: ViewModel() {

    val title = MutableLiveData<String>()
    val tag = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    private val articles = FirebaseFirestore.getInstance()
        .collection("articles")

    fun addData(title: String?, tag: String?, content: String?) {
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "tag" to tag
        )
        document.set(data)
    }
}