package com.dnar.dicodingsubmissionbfaa.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Data Class User; Keyword : DataClass
@Parcelize
data class User(
    val bio: String?,
    val avatar_url: String?,
    val blog: String?,
    val company: String?,
    val email: String?,
    val created_at: Long,
    val events_url: String?,
    val followers: Int,
    val followers_url: String?,
    val following: Int,
    val following_url: String?,
    val gists_url: String?,
    val hireable: String?,
    val gravatar_id: String?,
    val html_url: String?,
    val id: Int,
    val location: String?,
    val login: String?,
    val name: String?,
    val node_id: String?,
    val organizations_url: String?,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String?,
    val repos_url: String?,
    val score: Double?,
    val site_admin: Boolean?,
    val starred_url: String?,
    val twitter_username: String?,
    val subscriptions_url: String?,
    val type: String?,
    val updated_at: Long,
    val url: String?
) : Parcelable