package com.dnar.dicodingsubmissionbfaa.data.db.provider

import android.app.Application
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.dnar.dicodingsubmissionbfaa.data.db.AppDatabase
import com.dnar.dicodingsubmissionbfaa.ui.widget.UserWidget
import com.dnar.dicodingsubmissionbfaa.util.DATABASE_AUTHORITY
import com.dnar.dicodingsubmissionbfaa.util.USER_CONTENT_URI
import com.dnar.dicodingsubmissionbfaa.util.USER_TABLE_NAME
import com.dnar.dicodingsubmissionbfaa.util.toUserEntity
import dagger.android.DaggerContentProvider
import javax.inject.Inject

// Content Provider for User; Keyword : Room
class UserContentProvider : DaggerContentProvider() {

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var application: Application

    companion object {

        private const val USER = 1
        private const val USER_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            // Uri for select all, ex : content://com.dnar.dicodingsubmissionbfaa/users_favorite
            uriMatcher.addURI(DATABASE_AUTHORITY, USER_TABLE_NAME, USER)

            // Uri for select one by id, ex : content://com.dnar.dicodingsubmissionbfaa/users_favorite/id
            uriMatcher.addURI(DATABASE_AUTHORITY, "$USER_TABLE_NAME/#", USER_ID)
        }
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        // Implement this to handle query requests from clients.
        return when (uriMatcher.match(uri)) {
            USER -> db.getUserDao().getUsers()
            USER_ID -> uri.lastPathSegment?.toInt()?.let { db.getUserDao().getUserById(it) }
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Implement this to handle requests to insert a new row.
        val added: Long = when (USER) {
            uriMatcher.match(uri) -> values?.toUserEntity()?.let {
                db.getUserDao().insertUser(
                    it
                )
            } ?: 0
            else -> 0
        }

        context?.contentResolver?.notifyChange(USER_CONTENT_URI.toUri(), null)

        // Refresh data in UserWidget
        refreshWidgetUser()

        return Uri.parse("$USER_CONTENT_URI/$added")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        // Implement this to handle requests to update one or more rows.
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implement this to handle requests to delete one or more rows
        val deleted: Int = when (USER_ID) {
            uriMatcher.match(uri) -> uri.lastPathSegment?.toInt()?.let {
                db.getUserDao().deleteUser(
                    it
                )
            } ?: 0
            else -> 0
        }

        context?.contentResolver?.notifyChange(USER_CONTENT_URI.toUri(), null)

        // Refresh data in UserWidget
        refreshWidgetUser()

        return deleted
    }

    override fun getType(uri: Uri): String? {
        // Implement this to handle requests for the MIME type of the data at the given URI
        return null
    }

    private fun refreshWidgetUser() {
        // Refresh data in UserWidget
        UserWidget.sendRefreshBroadcast(application)
    }
}
