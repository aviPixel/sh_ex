package com.sh.ex.mainmenu.view

import android.graphics.Point
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.sh.ex.R
import com.sh.ex.mainmenu.interfaces.IMain
import com.sh.ex.mainmenu.viewmodel.MainViewModel
import com.sh.ex.mainmenu.viewmodel.MainViewModelFactory
import com.sh.ex.menublank.BlankFragment
import com.sh.ex.menuone.view.OneFragment
import com.sh.ex.menuthree.view.ThreeFragment
import com.sh.ex.newgoal.view.NewGoalFragment
import core.util.setWidthDisplay
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.view_main_menu.*
import java.io.IOException


class MainActivity : AppCompatActivity(), IMain.View, IMain.Navigation, IMain.Api {

    companion object {
        private const val TAG_FRAGMENT_ONE = "TAG_FRAGMENT_ONE"
        private const val TAG_FRAGMENT_TWO = "TAG_FRAGMENT_TWO"
        private const val TAG_FRAGMENT_THREE = "TAG_FRAGMENT_THREE"
        private const val TAG_FRAGMENT_FOUR = "TAG_FRAGMENT_FOUR"
        private const val TAG_FRAGMENT_NEW_GOAL = "TAG_FRAGMENT_NEW_GOAL"
    }

    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(this)).get((MainViewModel::class.java))
    }

    private var mSocket: Socket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        initObserve()

        showOneFragment()
        setIconMenu(imgOne)
    }

    override fun initView() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x
        val height: Int = size.y

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val heightPixels = metrics.heightPixels
        val widthPixels = metrics.widthPixels

        setWidthDisplay(widthPixels)
        frameOne.setOnClickListener {
            showOneFragment()
            setIconMenu(imgOne)
        }
        frameTwo.setOnClickListener {
            showTwoFragment()
            setIconMenu(imgTwo)
        }
        frameThree.setOnClickListener {
            showThreeFragment()
            setIconMenu(imgThree)
        }
        frameFour.setOnClickListener {
            showFourFragment()
            setIconMenu(imgFour)
        }
    }

    override fun setIconMenu(icon: ImageView?) {
        imgOne?.clearColorFilter()
        imgTwo?.clearColorFilter()
        imgThree?.clearColorFilter()
        imgFour?.clearColorFilter()
        icon?.setColorFilter(ContextCompat.getColor(this, R.color.colorButtonNewGoal))
    }

    override fun initObserve() {
        try {
            mSocket = IO.socket("https://px-socket-api.herokuapp.com/")
            mSocket?.connect()
            mSocket?.on(Socket.EVENT_CONNECT, Emitter.Listener {
                mSocket?.emit("new-notification", "")
//                runOnUiThread {
//                    val cdt = object : CountDownTimer(10000, 2000) {
//                        override fun onTick(millisUntilFinished: Long) {
//                            apiUpdate()
//                        }
//                        override fun onFinish() {}
//                    }.start()
//                }
            })
            mSocket?.on("new-notification", onUpdate)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private var onUpdate = Emitter.Listener {
//        [{"nameValuePairs":{"message":"a1791e78-1a90-4cce-b815-cb40cedf3067"}}]
        runOnUiThread {
            val numBadge = if (tvCountBadge.text.toString() == "") 0 else tvCountBadge.text.toString().toInt()
            tvCountBadge.text = (numBadge + 1).toString()
            tvCountBadge.visibility = View.VISIBLE
        }
    }

    override fun apiUpdate() {
        viewModel.apiUpdate()
    }

    override fun showOneFragment() {
        changeFragment(OneFragment.newInstance(), TAG_FRAGMENT_ONE)
    }

    override fun showTwoFragment() {
        changeFragment(BlankFragment.newInstance(), TAG_FRAGMENT_TWO)
    }

    override fun showThreeFragment() {
        changeFragment(ThreeFragment.newInstance(), TAG_FRAGMENT_THREE)
    }

    override fun showFourFragment() {
        changeFragment(BlankFragment.newInstance(), TAG_FRAGMENT_FOUR)
    }

    override fun showNewGoalFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainContentContainer, NewGoalFragment.newInstance(), TAG_FRAGMENT_NEW_GOAL)
            .addToBackStack(TAG_FRAGMENT_NEW_GOAL)
            .commit()
    }

    override fun apiUpdateSuccess() {

    }

    override fun apiUpdateFail(code: String, message: String) {
        Toast.makeText(this, "$code  $message", Toast.LENGTH_SHORT).show()
    }

    override fun changeFragment(fragment: Fragment, tag: String) {
        val newGoalFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_NEW_GOAL)
        if (newGoalFragment is NewGoalFragment) {
            supportFragmentManager.popBackStack(TAG_FRAGMENT_NEW_GOAL, FragmentManager.POP_BACK_STACK_INCLUSIVE)//0
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContentContainer, fragment)
            .commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_NEW_GOAL)
        if (fragment is NewGoalFragment) {
            supportFragmentManager.popBackStack(TAG_FRAGMENT_NEW_GOAL, FragmentManager.POP_BACK_STACK_INCLUSIVE)//0
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket?.disconnect()
    }

}