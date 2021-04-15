package teknoloji.atolyesi.photosharingapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
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

    private lateinit var binding : FragmentProfilePageBinding

    private val navSelected = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.homeButton -> {
                val action = ProfilePageFragmentDirections.actionProfilePageFragmentToHomePage()
                this.findNavController().navigate(action)
               return@OnNavigationItemSelectedListener true
            }
            R.id.addButton -> {
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            }
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
    }
}