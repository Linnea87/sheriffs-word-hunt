package com.example.sheriffswordhunt.ui.common

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.sheriffswordhunt.R

// ========== UI HELPER: STORY EVENT DIALOGS ==============================
// Provides reusable dialogs for case unlocked and bandit captured events.

class DialogHelper(private val activity: Activity) {

    // ========== CASE UNLOCKED =============================================

    fun showCaseUnlockedDialog(
        onContinue: () -> Unit,
        onBackToTown: () -> Unit
    ) {
        val view = inflateDialog()

        val title = view.findViewById<TextView>(R.id.tv_dialog_title)
        val message = view.findViewById<TextView>(R.id.tv_dialog_message)
        val btnNegative = view.findViewById<AppCompatButton>(R.id.dialog_btn_negative)
        val btnPositive = view.findViewById<AppCompatButton>(R.id.dialog_btn_positive)

        title.text = activity.getString(R.string.dialog_case_unlocked_title)
        message.text = activity.getString(R.string.dialog_case_unlocked_message)

        val dialog = AlertDialog.Builder(activity)
            .setView(view)
            .setCancelable(false)
            .create()

        btnPositive.text = activity.getString(R.string.dialog_button_continue)
        btnPositive.setOnClickListener {
            dialog.dismiss()
            onContinue()
        }

        btnNegative.text = activity.getString(R.string.dialog_button_back_to_town)
        btnNegative.setOnClickListener {
            dialog.dismiss()
            onBackToTown()
        }

        dialog.show()
    }

    // ========== BANDIT CAPTURED =============================================

    fun showBanditCapturedDialog(
        messageRes: Int,
        showContinue: Boolean,
        onContinue: () -> Unit,
        onBackToTown: () -> Unit
    ) {
        val view = inflateDialog()

        val title = view.findViewById<TextView>(R.id.tv_dialog_title)
        val message = view.findViewById<TextView>(R.id.tv_dialog_message)
        val btnNegative = view.findViewById<AppCompatButton>(R.id.dialog_btn_negative)
        val btnPositive = view.findViewById<AppCompatButton>(R.id.dialog_btn_positive)

        title.text = activity.getString(R.string.dialog_bandit_captured_title)
        message.text = activity.getString(messageRes)

        val dialog = AlertDialog.Builder(activity)
            .setView(view)
            .setCancelable(false)
            .create()

        if (showContinue) {
            btnPositive.text = activity.getString(R.string.dialog_button_continue)
            btnPositive.setOnClickListener {
                dialog.dismiss()
                onContinue()
            }

            btnNegative.text = activity.getString(R.string.dialog_button_back_to_town)
            btnNegative.setOnClickListener {
                dialog.dismiss()
                onBackToTown()
            }

        } else {
            btnPositive.visibility = View.GONE

            btnNegative.text = activity.getString(R.string.dialog_button_back_to_town)
            btnNegative.setOnClickListener {
                dialog.dismiss()
                onBackToTown()
            }
        }

        dialog.show()
    }

    // ========== INTERNAL =============================================

    private fun inflateDialog(): View {
        return activity.layoutInflater.inflate(R.layout.dialog_story_event, null)
    }
}