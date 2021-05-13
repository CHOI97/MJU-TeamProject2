package com.example.mtmimyeon_gitmi.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mtmimyeon_gitmi.HomeActivity
import com.example.mtmimyeon_gitmi.R
import com.example.mtmimyeon_gitmi.databinding.ActivityLoginBinding
import com.royrodriguez.transitionbutton.TransitionButton

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewLoginSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out)
            }

        }

        binding.buttonLoginSignIn.setOnClickListener {
            // Start the loading animation when the user tap the button
            binding.buttonLoginSignIn.startAnimation()

            // Do your networking task or background work here.
            val handler: Handler = Handler()
            handler.postDelayed(Runnable {
                val isSuccessful: Boolean = true

                // Choose a stop animation if your call was successful or not
                if (isSuccessful) {
                    binding.buttonLoginSignIn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND,
                        TransitionButton.OnAnimationStopEndListener {
                            Intent(this, HomeActivity::class.java).also {
                                startActivity(it)
                                finish() // 임시
                            }
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