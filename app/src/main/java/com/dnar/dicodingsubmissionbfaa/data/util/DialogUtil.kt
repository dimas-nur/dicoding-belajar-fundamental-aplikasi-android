package com.dnar.dicodingsubmissionbfaa.data.util

import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R

fun showDialogWarning(dialog: SweetAlertDialog, message: String, method: (() -> Unit)?) {
    // Start error dialog
    dialog.apply {
        changeAlertType(SweetAlertDialog.WARNING_TYPE)
        titleText = dialog.context.getString(R.string.placeholder_error_tittle)
        contentText = dialog.context.getString(R.string.placeholder_error_message)
        setConfirmClickListener {
            method?.let {
                method.invoke()
            }
            dismiss()
        }
        if (!this.isShowing) {
            show()
        }
    }
    // Log error
    Log.d(dialog.context::class.simpleName?.take(23), "error : $message")
}

fun hideDialog(dialog: SweetAlertDialog) {
    dialog.dismiss()
}