package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityRegisterBinding
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Navigate to login activity when "Login Here" is clicked
        binding.activityRegisterTvLoginHere.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Register button click listener
        binding.activityRegisterBtRegister.setOnClickListener{
            // Insert user data and clear input fields
            insertData()
            clearAll()
        }
    }
    // Function to insert user data into Firebase Realtime Database
    @RequiresApi(Build.VERSION_CODES.O)
    fun insertData() {
    // Create a User object with input data
        var user = User(binding.activityRegisterEtFirstName.text.toString(),
                        binding.activityRegisterEtLastName.text.toString(),
                        binding.activityRegisterEtPhone.text.toString(),
                        binding.activityRegisterEtDescription.text.toString(),
                        binding.activityRegisterEtUsername.text.toString(),
                        binding.activityRegisterEtPassword.text.toString(),
                        binding.activityRegisterEtProfilePicture.text.toString(),
                        binding.activityRegisterEtFullAddress.text.toString(),
                        binding.activityRegisterEtCountry.text.toString(),
                        binding.activityRegisterEtRegion.text.toString(),
                        binding.activityRegisterEtCity.text.toString(),
                        binding.activityRegisterEtStreet.text.toString(),
                        getDate())


        // Push user data to "users" node in the Firebase Realtime Database
        FirebaseDatabase.getInstance().getReference().child("users").push()
            .setValue(user)
            .addOnSuccessListener {
                // The data was inserted successfully
                Toast.makeText(this, "Account registration successful!", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                // The data insertion failed
                Toast.makeText(this, "Account registration failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
    }
    // Function to clear all input fields
    fun clearAll() {
        binding.activityRegisterEtFirstName.text.clear()
        binding.activityRegisterEtLastName.text.clear()
        binding.activityRegisterEtPhone.text.clear()
        binding.activityRegisterEtDescription.text.clear()
        binding.activityRegisterEtUsername.text.clear()
        binding.activityRegisterEtPassword.text.clear()
        binding.activityRegisterEtProfilePicture.text.clear()
        binding.activityRegisterEtFullAddress.text.clear()
        binding.activityRegisterEtStreet.text.clear()
        binding.activityRegisterEtCity.text.clear()
        binding.activityRegisterEtRegion.text.clear()
        binding.activityRegisterEtCountry.text.clear()
    }

    // Function to get the current date in a formatted string
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(): String {
        // Get the current date
        val today: LocalDate = LocalDate.now()

        // Create a formatter with the desired format
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

        // Format the date using the formatter
        val formattedDate: String = today.format(formatter)

        return formattedDate
    }
}