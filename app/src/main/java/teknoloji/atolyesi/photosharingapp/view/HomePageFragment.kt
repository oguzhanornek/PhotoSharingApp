package teknoloji.atolyesi.photosharingapp.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import teknoloji.atolyesi.photosharingapp.R
import teknoloji.atolyesi.photosharingapp.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {
private lateinit var binding : FragmentHomePageBinding
private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonExit.setOnClickListener(){
            auth.signOut()
            val action = HomePageFragmentDirections.actionHomePageToLoginScreen()
            this.findNavController().navigate(action)
            }
        binding.goProfileButton.setOnClickListener(){
            val action2 = HomePageFragmentDirections.actionHomePageToProfilePageFragment()
            this.findNavController().navigate(action2)
        }
    }
}