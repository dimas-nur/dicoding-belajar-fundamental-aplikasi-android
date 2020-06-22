package com.dnar.consumerapp.ui.main.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dnar.consumerapp.R
import com.dnar.consumerapp.util.changeNavigation

// Home fragment implements fragment
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate to HomeFragment in 2.5s
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            view.changeNavigation(action)
        }, 2500)
    }
}