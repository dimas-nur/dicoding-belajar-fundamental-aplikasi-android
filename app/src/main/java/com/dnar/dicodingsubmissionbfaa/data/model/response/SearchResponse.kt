package com.dnar.dicodingsubmissionbfaa.data.model.response

import com.dnar.dicodingsubmissionbfaa.data.model.User

// Data Class SearchResponse; Keyword : DataClass
data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<User>,
    val total_count: Int
)