package teknoloji.atolyesi.photosharingapp.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import teknoloji.atolyesi.photosharingapp.R
import teknoloji.atolyesi.photosharingapp.databinding.FragmentProfilePageBinding


class ProfilePageFragment : Fragment() {
    var chosenImage : Uri? = null
    var chosenBitmap : Bitmap? = null

    private lateinit var binding : FragmentProfilePageBinding


    private val navSelected = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.homeButton -> {
                val action = ProfilePageFragmentDirections.actionProfilePageFragmentToHomePage()
                this.findNavController().navigate(action)
               return@OnNavigationItemSelectedListener true
            }
           /* R.id.addButton -> {
                // izin yoksa izin alınacak
                if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)}
                else{
                    // izin zaten varsa yapılacak
                activity?.let {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent, 2)
                }
                }
            }*/
        }
        return@OnNavigationItemSelectedListener false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_page,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavView.setOnNavigationItemSelectedListener(navSelected)

        binding.addProfilePhotoButton.setOnClickListener(){
            if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)}
            else{
                // izin zaten varsa yapılacak
                activity?.let {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent, 2)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1){
            if (grantResults.size>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                activity?.let {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent, 2)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode ==2 && resultCode == Activity.RESULT_OK && data != null)

            chosenImage = data.data
        if (chosenImage != null){

            if (Build.VERSION.SDK_INT>=28){
                val source = ImageDecoder.createSource(requireContext().contentResolver,chosenImage!!)
                chosenBitmap = ImageDecoder.decodeBitmap(source)
                binding.addProfilePhotoButton.setImageBitmap(chosenBitmap)
            }else{
                chosenBitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver,chosenImage)
                binding.profilePhoto.setImageBitmap(chosenBitmap)
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}