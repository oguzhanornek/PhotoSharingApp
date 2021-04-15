package teknoloji.atolyesi.photosharingapp.view


import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import teknoloji.atolyesi.photosharingapp.R
import teknoloji.atolyesi.photosharingapp.databinding.FragmentSigninScreenBinding



class SigninScreenFragment : Fragment() {
    private lateinit var signBinding: FragmentSigninScreenBinding
    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fAuth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signin_screen,container,false)
        return signBinding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signBinding.SignButton.setOnClickListener(){

            val name = signBinding.SignNameText.text.toString().trim()
            val mail = signBinding.SignEmailText.text.toString().trim()
            val password = signBinding.SignPasswordText.text.toString().trim()
            
            fAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener {

                if (it.isSuccessful){
                    val action = SigninScreenFragmentDirections.actionSigninScreenToHomePage()
                    Navigation.findNavController(view).navigate(action)

                }
            }.addOnFailureListener {
                Toast.makeText(context,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

        signBinding.SignHavaAccountText.setOnClickListener(){

            val action2= SigninScreenFragmentDirections.actionSigninScreenToLoginScreen()
            Navigation.findNavController(view).navigate(action2)
        }
    }
    }
