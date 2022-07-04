package com.mialkan.talkbackjourney

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.mialkan.talkbackjourney.databinding.ActivityTalkBackBinding
import kotlin.random.Random

class TalkBackActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTalkBackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTalkBackBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setUpControl()
        viewBinding.root.findViewById<Toolbar?>(R.id.toolbar)?.title = "Talk Back Activity"
    }

    private fun setUpControl() {
        viewBinding.root.findViewById<MaterialButton>(R.id.btn1)?.run {
            this.text = Random.nextInt(0, 1000000).toString()
            setOnClickListener {
                openNewActivity()
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn4)?.run {
            this.text = Random.nextInt(0, 1000000).toString()
            setOnClickListener {
                openNewActivity()
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn7)?.run {
            this.text = Random.nextInt(0, 1000000).toString()
            setOnClickListener {
                openNewActivity()
            }
        }

        viewBinding.root.findViewById<MaterialButton>(R.id.btn2)?.run {
            this.text = "Button 2"
            setOnClickListener {
                openNewActivity()
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn5)?.run {
            this.text = "Button 5"
            setOnClickListener {
                openNewActivity()
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn8)?.run {
            this.text = "Button 8"
            setOnClickListener {
                openNewActivity()
            }
        }

        viewBinding.root.findViewById<MaterialButton>(R.id.btn3)?.run {
            this.text = "Nav " + Random.nextInt(0, 1000).toString()
            setOnClickListener {
                openNewActivity()
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn6)?.run {
            this.text = "Nav " + Random.nextInt(0, 1000).toString()
            setOnClickListener {
                openNewActivity()
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn9)?.run {
            this.text = "Nav " + Random.nextInt(0, 1000).toString()
            setOnClickListener {
                openNewActivity()
            }
        }
    }

    private fun openNewActivity() {
        startActivity(Intent(this, TalkBackActivity::class.java))
        finish()
    }
}
