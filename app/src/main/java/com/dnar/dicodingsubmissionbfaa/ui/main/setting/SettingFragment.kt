package com.dnar.dicodingsubmissionbfaa.ui.main.setting

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.service.AlarmHelper
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentSettingBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.util.ALARM_ID_REPEATING
import com.dnar.dicodingsubmissionbfaa.util.SP_KEY_REMINDER
import com.shashank.sony.fancytoastlib.FancyToast
import java.util.*
import javax.inject.Inject

// Setting fragment implements dagger fragment
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override var getLayoutId: Int = R.layout.fragment_setting
    override var getViewModel: Class<SettingViewModel> = SettingViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Setting")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure ViewBinding
        mViewBinding.apply {

            // Set button BackToolbar onClickListener
            settingBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }

            // Set button ChangeLanguage onClickListener
            settingBtnChangeLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }

            // Set button ChangeLanguage onClickListener
            settingSwitchReminder.apply {

                // Get state reminder from SharedPreferences
                isChecked = sharedPreferences.getBoolean(SP_KEY_REMINDER, true)

                // Change state reminder from SharedPreferences
                setOnCheckedChangeListener { _, isChecked ->
                    setReminder(isChecked)
                }
            }
        }
    }

    // Save reminder state to SharedPreferences
    private fun setReminder(reminder: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(SP_KEY_REMINDER, reminder)
        }.apply()

        context?.let {
            if (reminder) {
                // Create reminder alarm
                AlarmHelper.createAlarm(
                    it,
                    getString(R.string.app_name),
                    "Let's find popular user on Github!",
                    ALARM_ID_REPEATING,
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 9)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }
                )

                // Show toast
                FancyToast.makeText(
                    context,
                    getString(R.string.setting_turn_on_reminder),
                    FancyToast.LENGTH_SHORT,
                    FancyToast.SUCCESS,
                    false
                ).show()
            } else {
                // Delete reminder alarm
                AlarmHelper.cancelAlarm(it, ALARM_ID_REPEATING)

                // Show toast
                FancyToast.makeText(
                    context,
                    getString(R.string.setting_turn_off_reminder),
                    FancyToast.LENGTH_SHORT,
                    FancyToast.SUCCESS,
                    false
                ).show()
            }
        }
    }
}