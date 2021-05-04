package com.example.mtmimyeon_gitmi.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mtmimyeon_gitmi.HomeActivity
import com.example.mtmimyeon_gitmi.databinding.ActivityLoginBinding
import com.royrodriguez.transitionbutton.TransitionButton

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
//         with(window) { // activity 옆으로 이동 애니메이션
//            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//            // set an slide transition
//            enterTransition = Slide(Gravity.END)
//            exitTransition = Slide(Gravity.START)
//        }

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewLoginSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLoginSignIn.setOnClickListener {
            // Start the loading animation when the user tap the button
            binding.buttonLoginSignIn.startAnimation()

            // Do your networking task or background work here.
            val handler: Handler = Handler()
            handler.postDelayed(Runnable {
                val isSuccessful: Boolean = true

                // Choose a stop animation if your call was succesful or not
                if (isSuccessful) {
                    binding.buttonLoginSignIn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND,
                        TransitionButton.OnAnimationStopEndListener {
                            val intent = Intent(this, HomeActivity::class.java)
                            //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                            startActivity(intent)
                            finish() // 임시
                        })
                } else {
                    binding.buttonLoginSignIn.stopAnimation(
                        TransitionButton.StopAnimationStyle.SHAKE,
                        null
                    )
                }
            }, 300)


        }
    }
}