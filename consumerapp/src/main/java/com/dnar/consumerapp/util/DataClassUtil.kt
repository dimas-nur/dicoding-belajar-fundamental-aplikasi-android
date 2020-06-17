package com.dnar.consumerapp.util

import android.content.ContentValues
import android.database.Cursor
import com.dnar.consumerapp.data.model.UserDetail
import java.util.*

// Function : for convert from UserDetail to ContentValues
fun UserDetail.toContentValues(): ContentValues =
    ContentValues().apply {
        put(USER_ID, id)
        put(USER_AVATAR_URL, avatar_url)
        put(USER_BIO, bio)
        put(USER_BLOG, blog)
        put(USER_COMPANY, company)
        put(USER_CREATED_AT, created_at)
        put(USER_EMAIL, email)
        put(USER_EVENTS_URL, events_url)
        put(USER_FOLLOWERS, followers)
        put(USER_FOLLOWERS_URL, followers_url)
        put(USER_FOLLOWING, following)
        put(USER_FOLLOWING_URL, following_url)
        put(USER_GISTS_URL, gists_url)
        put(USER_GRAVATAR_ID, gravatar_id)
        put(USER_HIREABLE, hireable)
        put(USER_HTML_URL, html_url)
        put(USER_LOCATION, location)
        put(USER_LOGIN, login)
        put(USER_NAME, name)
        put(USER_NODE_ID, node_id)
        put(USER_ORGANIZATIONS_URL, organizations_url)
        put(USER_PUBLIC_GISTS, public_gists)
        put(USER_PUBLIC_REPOS, public_repos)
        put(USER_RECEIVED_EVENTS_URL, received_events_url)
        put(USER_REPOS_URL, repos_url)
        put(USER_SITE_ADMIN, site_admin)
        put(USER_STARRED_URL, starred_url)
        put(USER_SUBSCRIPTIONS_URL, subscriptions_url)
        put(USER_TWITTER_USERNAME, twitter_username)
        put(USER_TYPE, type)
        put(USER_UPDATED_AT, updated_at)
        put(USER_URL, url)
    }

// Function : for convert from ContentValues to UserDetail
fun ContentValues.toUserDetail(): UserDetail =
    UserDetail(
        id = getAsInteger(USER_ID),
        avatar_url = getAsString(USER_AVATAR_URL),
        bio = getAsString(USER_BIO),
        blog = getAsString(USER_BLOG),
        company = getAsString(USER_COMPANY),
        created_at = getAsString(USER_CREATED_AT),
        email = getAsString(USER_EMAIL),
        events_url = getAsString(USER_EVENTS_URL),
        followers = getAsInteger(USER_FOLLOWERS),
        followers_url = getAsString(USER_FOLLOWERS_URL),
        following = getAsInteger(USER_FOLLOWING),
        following_url = getAsString(USER_FOLLOWING_URL),
        gists_url = getAsString(USER_GISTS_URL),
        gravatar_id = getAsString(USER_GRAVATAR_ID),
        hireable = getAsString(USER_HIREABLE),
        html_url = getAsString(USER_HTML_URL),
        location = getAsString(USER_LOCATION),
        login = getAsString(USER_LOGIN),
        name = getAsString(USER_NAME),
        node_id = getAsString(USER_NODE_ID),
        organizations_url = getAsString(USER_ORGANIZATIONS_URL),
        public_gists = getAsInteger(USER_PUBLIC_GISTS),
        public_repos = getAsInteger(USER_PUBLIC_REPOS),
        received_events_url = getAsString(USER_RECEIVED_EVENTS_URL),
        repos_url = getAsString(USER_REPOS_URL),
        site_admin = getAsBoolean(USER_SITE_ADMIN),
        starred_url = getAsString(USER_STARRED_URL),
        subscriptions_url = getAsString(USER_SUBSCRIPTIONS_URL),
        twitter_username = getAsString(USER_TWITTER_USERNAME),
        type = getAsString(USER_TYPE),
        updated_at = getAsString(USER_UPDATED_AT),
        url = getAsString(USER_URL)
    )

fun Cursor.toListUserDetail(): ArrayList<UserDetail> {
    val userDetailList = ArrayList<UserDetail>()

    apply {
        while (moveToNext()) {
            userDetailList.add(
                toUserDetail()
            )
        }
    }
    return userDetailList
}

fun Cursor.toUserDetail(): UserDetail =
    UserDetail(
        getInt(getColumnIndexOrThrow(USER_ID)),
        getString(getColumnIndexOrThrow(USER_AVATAR_URL)),
        getString(getColumnIndexOrThrow(USER_BIO)),
        getString(getColumnIndexOrThrow(USER_BLOG)),
        getString(getColumnIndexOrThrow(USER_COMPANY)),
        getString(getColumnIndexOrThrow(USER_CREATED_AT)),
        getString(getColumnIndexOrThrow(USER_EMAIL)),
        getString(getColumnIndexOrThrow(USER_EVENTS_URL)),
        getInt(getColumnIndexOrThrow(USER_FOLLOWERS)),
        getString(getColumnIndexOrThrow(USER_FOLLOWERS_URL)),
        getInt(getColumnIndexOrThrow(USER_FOLLOWING)),
        getString(getColumnIndexOrThrow(USER_FOLLOWING_URL)),
        getString(getColumnIndexOrThrow(USER_GISTS_URL)),
        getString(getColumnIndexOrThrow(USER_GRAVATAR_ID)),
        getString(getColumnIndexOrThrow(USER_HIREABLE)),
        getString(getColumnIndexOrThrow(USER_HTML_URL)),
        getString(getColumnIndexOrThrow(USER_LOCATION)),
        getString(getColumnIndexOrThrow(USER_LOGIN)),
        getString(getColumnIndexOrThrow(USER_NAME)),
        getString(getColumnIndexOrThrow(USER_NODE_ID)),
        getString(getColumnIndexOrThrow(USER_ORGANIZATIONS_URL)),
        getInt(getColumnIndexOrThrow(USER_PUBLIC_GISTS)),
        getInt(getColumnIndexOrThrow(USER_PUBLIC_REPOS)),
        getString(getColumnIndexOrThrow(USER_RECEIVED_EVENTS_URL)),
        getString(getColumnIndexOrThrow(USER_REPOS_URL)),
        (getInt(getColumnIndexOrThrow(USER_SITE_ADMIN)) > 0),
        getString(getColumnIndexOrThrow(USER_STARRED_URL)),
        getString(getColumnIndexOrThrow(USER_SUBSCRIPTIONS_URL)),
        getString(getColumnIndexOrThrow(USER_TWITTER_USERNAME)),
        getString(getColumnIndexOrThrow(USER_TYPE)),
        getString(getColumnIndexOrThrow(USER_UPDATED_AT)),
        getString(getColumnIndexOrThrow(USER_URL))
    )