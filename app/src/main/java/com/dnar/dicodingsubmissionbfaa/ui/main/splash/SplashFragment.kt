package com.dnar.dicodingsubmissionbfaa.ui.main.splash

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.service.AlarmHelper
import com.dnar.dicodingsubmissionbfaa.util.ALARM_ID_REPEATING
import com.dnar.dicodingsubmissionbfaa.util.SP_KEY_REMINDER
import com.dnar.dicodingsubmissionbfaa.util.changeNavigation
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

// Home fragment implements fragment
class SplashFragment : DaggerFragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check is SharedPreferences contains reminder
        if (!sharedPreferences.contains(SP_KEY_REMINDER)) {

            // Set default value reminder to true
            sharedPreferences.edit().apply {
                putBoolean(SP_KEY_REMINDER, true)
            }.apply()

            // Create reminder alarm
            context?.let {
                AlarmHelper.createAlarm(
                    it,
                    getString(R.string.app_name),
                    getString(R.string.reminder_message),
                    ALARM_ID_REPEATING,
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 9)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }
                )
            }
        }

        // Navigate to HomeFragment in 2.5s
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            view.changeNavigation(action)
        }, 2500)
    }
}