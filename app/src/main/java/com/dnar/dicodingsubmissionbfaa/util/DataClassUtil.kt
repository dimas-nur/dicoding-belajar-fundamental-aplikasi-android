package com.dnar.dicodingsubmissionbfaa.util

import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.model.UserDetail

fun UserDetail.toUserEntity(): UserEntity {
    this.apply {
        return UserEntity(
            id = id,
            avatar_url = avatar_url,
            bio = bio,
            blog = blog,
            company = company,
            created_at = created_at,
            email = email,
            events_url = events_url,
            followers = followers,
            followers_url = followers_url,
            following = following,
            following_url = following_url,
            gists_url = gists_url,
            gravatar_id = gravatar_id,
            hireable = hireable,
            html_url = html_url,
            location = location,
            login = login,
            name = name,
            node_id = node_id,
            organizations_url = organizations_url,
            public_gists = public_gists,
            public_repos = public_repos,
            received_events_url = received_events_url,
            repos_url = repos_url,
            site_admin = site_admin,
            starred_url = starred_url,
            subscriptions_url = subscriptions_url,
            twitter_username = twitter_username,
            type = type,
            updated_at = updated_at,
            url = url
        )
    }
}

fun UserEntity.toUserDetail(): UserDetail {
    this.apply {
        return UserDetail(
            id = id,
            avatar_url = avatar_url,
            bio = bio,
            blog = blog,
            company = company,
            created_at = created_at,
            email = email,
            events_url = events_url,
            followers = followers,
            followers_url = followers_url,
            following = following,
            following_url = following_url,
            gists_url = gists_url,
            gravatar_id = gravatar_id,
            hireable = hireable,
            html_url = html_url,
            location = location,
            login = login,
            name = name,
            node_id = node_id,
            organizations_url = organizations_url,
            public_gists = public_gists,
            public_repos = public_repos,
            received_events_url = received_events_url,
            repos_url = repos_url,
            site_admin = site_admin,
            starred_url = starred_url,
            subscriptions_url = subscriptions_url,
            twitter_username = twitter_username,
            type = type,
            updated_at = updated_at,
            url = url
        )
    }
}