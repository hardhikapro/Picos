package com.example.picos.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.picos.R
import com.example.picos.ui.activity.CommingSoon
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
import java.util.*

class EditProfileFragment : Fragment() {
    private lateinit var imgProfile: CircleImageView
    private lateinit var firstNameEditText: EditText
    private lateinit var btnSave: Button
    private lateinit var database: DatabaseReference

    private var selectedImageUri: Uri? = null

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_IMAGE_GALLERY = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        imgProfile = view.findViewById(R.id.imgProfile)
        firstNameEditText = view.findViewById(R.id.firstName)
        btnSave = view.findViewById(R.id.btnSave)

        // Mengatur onClickListener untuk btnSave
        btnSave.setOnClickListener {
            saveChanges()
        }

        imgProfile.setOnClickListener {
            selectImage()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().reference


    }

    // Method untuk menyimpan perubahan nama dan foto profil
    private fun saveChanges() {
//        val newFirstName = firstNameEditText.text.toString()
//        database.child("users").child("firstName").setValue(newFirstName)
//
//        // Simpan newFirstName ke tempat penyimpanan yang sesuai (misalnya, Firebase Database)
//
//        if (selectedImageUri != null) {
//            // Jika ada gambar yang dipilih dari galeri atau diambil dari kamera
//            val selectedImageBitmap = BitmapFactory.decodeStream(context?.contentResolver?.openInputStream(selectedImageUri!!))
//            // Simpan selectedImageBitmap ke tempat penyimpanan yang sesuai (misalnya, Firebase Storage)
//            val imageUrl = uploadImageToStorage(selectedImageBitmap)
//            database.child("users").child("profileImageUrl").setValue(imageUrl)
//
//
//        }
        val intent = Intent(requireContext(), CommingSoon::class.java)
        startActivity(intent)

        // Tampilkan pesan sukses atau navigasikan ke halaman lain
    }

    private fun selectImage() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose your profile picture")
        builder.setItems(options) { dialog, item ->
            when {
                options[item] == "Take Photo" -> {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
                options[item] == "Choose from Gallery" -> {
                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY)
                }
                options[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }
        builder.show()
    }

    // Metode ini dipanggil ketika hasil dari intent kamera atau galeri dikembalikan
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgProfile.setImageBitmap(imageBitmap)
                    selectedImageUri = null // Jika gambar diambil dari kamera, tidak ada URI
                }
                REQUEST_IMAGE_GALLERY -> {
                    val imageUri = data?.data
                    imgProfile.setImageURI(imageUri)
                    selectedImageUri = imageUri // Mengupdate selectedImageUri dengan URI gambar yang dipilih dari galeri
                }
            }
        }
    }
    private fun uploadImageToStorage(imageBitmap: Bitmap): String {
        // Buat referensi ke Firebase Storage
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("profile_images")

        // Buat nama file unik untuk gambar
        val imageFileName = UUID.randomUUID().toString() + ".jpg"

        // Buat file reference di Firebase Storage
        val imageRef = imagesRef.child(imageFileName)

        // Convert Bitmap menjadi ByteArray
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageData = baos.toByteArray()

        // Upload gambar ke Firebase Storage
        val uploadTask = imageRef.putBytes(imageData)

        // Tambahkan listener untuk mendapatkan URL download gambar setelah berhasil diupload
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUrl = task.result.toString()
                // Lakukan operasi selanjutnya dengan URL gambar
            } else {
                // Handle error jika terjadi kesalahan saat mengupload gambar
            }
        }

        // Kembalikan URL gambar kosong untuk sementara
        return ""
    }
}
