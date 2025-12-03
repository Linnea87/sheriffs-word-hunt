package com.example.sheriffswordhunt.ui.common

import android.app.Activity
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.example.sheriffswordhunt.R

// ========== UI HELPER: CUSTOM TOAST =======================
// Displays a styled toast positioned under the hero section.

class ToastHelper(private val activity: Activity) {

    fun show(message: String) {
        val layout = activity.layoutInflater.inflate(R.layout.custom_toast, null)
        val textView = layout.findViewById<TextView>(R.id.tv_toast_message)
        textView.text = message

        @Suppress("DEPRECATION")
        val toast = Toast(activity).apply {
            duration = Toast.LENGTH_SHORT
            view = layout
        }

        val heroHeight = activity.resources.getDimensionPixelSize(R.dimen.hero_height)
        val offset = heroHeight + 20

        toast.setGravity(
            Gravity.CENTER_HORIZONTAL or Gravity.TOP,
            0,
            offset
        )

        toast.show()
    }
}