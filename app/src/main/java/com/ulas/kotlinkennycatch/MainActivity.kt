package com.ulas.kotlinkennycatch

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ulas.kotlinkennycatch.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ImageArray
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)


        hideImages()

        // CountDownTımer
        object  : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                binding.textTime.text = "Time : ${p0/1000} "
            }

            override fun onFinish() {

                binding.textTime.text = "Time Over!"
                handler.removeCallbacks(runnable)

                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                // alert dialog
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                    //restart
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game Ended!",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }


        }.start()
    }

    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {
                for(image in imageArray ){
                    image.visibility = View.INVISIBLE
                }
                val random = (0..8).random()
                imageArray[random].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }

  fun increaseScore(view: View){
      score = score +1
      binding.textScore.text = "Score : ${score}"

  }



}