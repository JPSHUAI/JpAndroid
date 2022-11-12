package com.jp.androidpro.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import com.drake.engine.base.EngineActivity
import com.jp.androidpro.databinding.ActivitySplashBinding
import com.jp.androidpro.ui.home.HomeActivity
import com.jp.androidpro.R

class SplashActivity : EngineActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initView() {
        if (!isTaskRoot) {
            val intent: Intent? = intent
            // 如果当前 Activity 是通过桌面图标启动进入的
            if (((intent != null) && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
                        && (Intent.ACTION_MAIN == intent.action))
            ) {
                // 对当前 Activity 执行销毁操作，避免重复实例化入口
                finish()
                return
            }
        }
        binding.lavSplashLottie.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                binding.lavSplashLottie.removeAnimatorListener(this)
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }
        })
    }

    override fun initData() {

    }

}