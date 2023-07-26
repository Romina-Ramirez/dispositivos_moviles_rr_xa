package com.example.aplicacionmovil.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts.*
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCapturar.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResult.launch(intent)
        }

        binding.imgCapture.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hola como vas ?")
            shareIntent.setType("text/plain")
            startActivity(Intent.createChooser(shareIntent, "Compartir"))
        }
    }

    private val cameraResult = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
               val image = result.data?.extras?.get("data") as Bitmap
                binding.imgCapture.setImageBitmap(image)
            }
            Activity.RESULT_CANCELED -> {}
        }
    }
}