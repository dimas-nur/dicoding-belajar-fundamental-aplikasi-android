package com.dnar.consumerapp.util

// Util for api; Keyword : Util

/* --- Url --- */
const val BASE_URL = "https://api.github.com/"


/* --- Token --- */
const val ACCESS_TOKEN = "429ab84e3da1db8210bb9c6e2474c492f81146b3"


/* --- Bundle --- */
const val ARG_FRAGMENT_KEY = "fragment_key"
const val ARG_FRAGMENT_VALUE_USERNAME = "fragment_value_username"
const val ARG_FRAGMENT_VALUE_USER = "fragment_value_user"


/* --- Alarm --- */
const val ALARM_ID_REPEATING = 100
const val ALARM_CHANNEL_ID = "channel_reminder"
const val ALARM_CHANNEL_NAME = "Reminder Channel"
const val ALARM_TITTLE = "alarm_tittle"
const val ALARM_MESSAGE = "alarm_message"


/* --- SharedPreferences --- */
const val SP_NAME = "dicoding_shared_preferences"
const val SP_KEY_REMINDER = "shared_preferences_reminder"


/* --- Database --- */
const val DATABASE_NAME = "DicodingSubmissionBFAA.db"
const val DATABASE_AUTHORITY = "com.dnar.dicodingsubmissionbfaa"
const val DATABASE_SCHEME = "content"
const val DATABASE_CONTENT_URI = "$DATABASE_SCHEME://$DATABASE_AUTHORITY"


/* --- Database Table User --- */
const val USER_TABLE_NAME = "users_favorite"
const val USER_CONTENT_URI = "$DATABASE_CONTENT_URI/$USER_TABLE_NAME"

const val USER_ID = "id"
const val USER_AVATAR_URL = "avatar_url"
const val USER_BIO = "bio"
const val USER_BLOG = "blog"
const val USER_COMPANY = "company"
const val USER_CREATED_AT = "created_at"
const val USER_EMAIL = "email"
const val USER_EVENTS_URL = "events_url"
const val USER_FOLLOWERS = "followers"
const val USER_FOLLOWERS_URL = "followers_url"
const val USER_FOLLOWING = "following"
const val USER_FOLLOWING_URL = "following_url"
const val USER_GISTS_URL = "gists_url"
const val USER_GRAVATAR_ID = "gravatar_id"
const val USER_HIREABLE = "hireable"
const val USER_HTML_URL = "html_url"
const val USER_LOCATION = "location"
const val USER_LOGIN = "login"
const val USER_NAME = "name"
const val USER_NODE_ID = "node_id"
const val USER_ORGANIZATIONS_URL = "organizations_url"
const val USER_PUBLIC_GISTS = "public_gists"
const val USER_PUBLIC_REPOS = "public_repos"
const val USER_RECEIVED_EVENTS_URL = "received_events_url"
const val USER_REPOS_URL = "repos_url"
const val USER_SITE_ADMIN = "site_admin"
const val USER_STARRED_URL = "starred_url"
const val USER_SUBSCRIPTIONS_URL = "subscriptions_url"
const val USER_TWITTER_USERNAME = "twitter_username"
const val USER_TYPE = "type"
const val USER_UPDATED_AT = "updated_at"
const val USER_URL = "url"