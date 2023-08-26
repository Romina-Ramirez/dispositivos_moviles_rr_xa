package com.example.aplicacionmovil.ui.fragments


import androidx.fragment.app.Fragment
import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

//camara y almacenamiento
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.data.entities.LoginUser
import com.example.aplicacionmovil.databinding.FragmentThirdBinding
import com.example.aplicacionmovil.logic.data.MarvelChars
import com.example.aplicacionmovil.logic.marvelLogic.MarvelCharactersLogic
import com.example.aplicacionmovil.ui.activities.DetailsMarvelItem
import com.example.aplicacionmovil.ui.activities.RegisterActivity
import com.example.aplicacionmovil.ui.adapters.MarvelAdapter
import com.example.aplicacionmovil.ui.adapters.MarvelAdapterItems
import com.example.aplicacionmovil.ui.adapters.MarvelBuscadorAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class ThirdFragment : Fragment()  {

    private lateinit var rvAdapter: MarvelAdapterItems

    private lateinit var binding: FragmentThirdBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private var marvelCharsItems: MutableList<MarvelChars> = mutableListOf<MarvelChars>()
    private var marvelCharsItemsDB: MutableList<MarvelChars> = mutableListOf<MarvelChars>()
    private lateinit var gManager: GridLayoutManager

    // Identificadores únicos para las solicitudes de permiso
    private val CAMERA_PERMISSION_REQUEST = 100
    private val STORAGE_PERMISSION_REQUEST = 101
    private val TAKE_PICTURE_REQUEST = 102
    private val PICK_IMAGE_REQUEST = 103


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentThirdBinding.inflate(
            layoutInflater, container,false

        )

        rvAdapter = MarvelAdapterItems({ sendMarvelItem(it) }, { saveMarvelItem(it) })

        showFavoriteComics()

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.favoriteRecyclerView.adapter = rvAdapter
        gManager = GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
        binding.favoriteRecyclerView.layoutManager = gManager

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Log.d("MyTag", "User is logged in with email: ${currentUser.email}")
            val userEmail = currentUser.email

            if (userEmail != null) {
                Log.d("MyTag", "Searching for user with email: $userEmail")
                binding.usuarioEmail.text = userEmail

            }
        } else {
            Log.d("MyTag", "No user is logged in")
        }



        binding.btnCambiarFoto.setOnClickListener {
            val options = arrayOf("Tomar Foto", "Seleccionar de la Galería")
            AlertDialog.Builder(requireContext()).setTitle("Elige una opción")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> {
                            // Compruebe que se concedió el permiso
                            if (ContextCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                abrirCamara()
                            } else {
                                // Si no fue concedido solicite de nuevo
                                ActivityCompat.requestPermissions(
                                    requireContext() as Activity,
                                    arrayOf(Manifest.permission.CAMERA),
                                    CAMERA_PERMISSION_REQUEST
                                )
                            }
                        }

                        1 -> {
                            // Opción: Seleccionar de la Galería
                            if (ContextCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                abrirGaleria()
                            } else {
                                ActivityCompat.requestPermissions(
                                    requireContext() as Activity,
                                    arrayOf(
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                    ),
                                    STORAGE_PERMISSION_REQUEST
                                )
                            }
                        }
                    }
                }
                .show()
        }

    }
    private fun sendMarvelItem(item: MarvelChars) {

    }

    private fun showFavoriteComics() {
        lifecycleScope.launch(Dispatchers.Main) {
            val savedMarvelChars = withContext(Dispatchers.IO) {
                MarvelCharactersLogic().getSavedMarvelChars()
            }


            rvAdapter.replaceListItems(savedMarvelChars)
        }
    }
    private fun saveMarvelItem(item: MarvelChars): Boolean {

        return false
    }


    private fun abrirCamara() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, TAKE_PICTURE_REQUEST)
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Maneja la respuesta a las solicitudes de permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirCamara()
                }
            }
            STORAGE_PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirGaleria()
                }
            }
        }
    }

    // Verifica si la actividad se completó correctamente
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            when (requestCode) {
                TAKE_PICTURE_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    binding.usuarioImg.setImageBitmap(imageBitmap)
                }
                PICK_IMAGE_REQUEST -> {
                    val imageUri: Uri = data?.data ?: return
                    binding.usuarioImg.setImageURI(imageUri)
                }
            }
        }

    }

    fun chargeDataRV(limit: Int,offset: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext (MarvelCharactersLogic().getAllMarvelChars(offset, limit))
            }
            rvAdapter.items = marvelCharsItems
            binding.favoriteRecyclerView.apply {
                this.adapter = rvAdapter
                this.layoutManager = gManager
            }
        }
    }
}