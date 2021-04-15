package teknoloji.atolyesi.photosharingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import teknoloji.atolyesi.photosharingapp.R
import teknoloji.atolyesi.photosharingapp.databinding.FragmentLoginScreenBinding


class LoginScreenFragment : Fragment() {
    private lateinit var loginBinding : FragmentLoginScreenBinding
    private lateinit var Auth : FirebaseAuth

     override fun onCreate(savedInstanceState: Bundle?) {
         Auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
         val currentUser = Auth.currentUser
         if (currentUser != null){
             val action = LoginScreenFragmentDirections.actionLoginScreenToHomePage()
             this.findNavController().navigate(action)
         }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_screen,container,false)
        // Inflate the layout for this fragment
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.LoginButton.setOnClickListener(){
            val mail = loginBinding.LoginEmailText.text.toString().trim()
            val password = loginBinding.LoginPasswordText.text.toString().trim()

            Auth.signInWithEmailAndPassword(mail,password).addOnCompleteListener {
                if (it.isSuccessful){

                    val action = LoginScreenFragmentDirections.actionLoginScreenToHomePage()
                    Navigation.findNavController(view).navigate(action)

                }
            }.addOnFailureListener {
                Toast.makeText(context,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        loginBinding.LoginHaveAccountText.setOnClickListener(){
            val action2 = LoginScreenFragmentDirections.actionLoginScreenToSigninScreen()
            Navigation.findNavController(view).navigate(action2)

        }
    }
}