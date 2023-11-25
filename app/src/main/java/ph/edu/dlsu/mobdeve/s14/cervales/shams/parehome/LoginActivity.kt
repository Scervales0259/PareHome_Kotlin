package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityLoginBinding

// Activity responsible for handling user login
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Login button click listener
        binding.activityLoginBtnLogin.setOnClickListener{
            // Show progress bar
            binding.activityLoginPgProgressBar.visibility = View.VISIBLE

            // Get the user email and password from the login form
            val username = binding.activityLoginEtEmail.text.toString().lowercase()
            val password = binding.activityLoginEtPassword.text.toString()

            // Firebase Realtime Database reference
            val reference = FirebaseDatabase.getInstance().getReference("users");

            if(username.isNotBlank() && password.isNotBlank()) {
                // Add a value listener to the user query
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var isUserExists = false
                        // Check if the user exists
                        for (childSnapshot in dataSnapshot.getChildren()) {
                            val uid = childSnapshot.key
                            val usernameReference = reference.child(uid!!).child("username")
                            usernameReference.get().addOnSuccessListener { dataSnapshot ->
                                // Checking the username
                                if (username.equals(dataSnapshot.getValue(String::class.java).toString())) {
                                    //Checking the password
                                    val passwordReference = reference.child(uid!!).child("password")
                                    passwordReference.get().addOnSuccessListener { dataSnapshot ->
                                        if (password.equals(dataSnapshot.getValue(String::class.java).toString())) {
                                            // Login successful
                                            Toast.makeText(
                                                this@LoginActivity,
                                                "Login successful!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            createSession(uid)
                                            isUserExists = true
                                            binding.activityLoginPgProgressBar.visibility = View.GONE
                                            var intent = Intent(this@LoginActivity, MainActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            // Invalid password
                                            Toast.makeText(
                                                this@LoginActivity,
                                                "Invalid password",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle the error
                        Log.e(
                            "LoginActivity",
                            "Error retrieving user data: ${databaseError.message}"
                        )
                    }
                })
            }
        }
        // Click listener for registration link
        binding.activityLoginTvRegisterHere.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.generateUserData.setOnClickListener{
//            var users = DataHelper.initializeUserData()
//
//            var i = 0
//            for(user in users) {
//                FirebaseDatabase.getInstance().getReference().child("users").push()
//                    .setValue(user)
//                    .addOnSuccessListener {
//                        // The data was inserted successfully
//                        Toast.makeText(this, "Generated User Data #${i} successful!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener {
//                        // The data insertion failed
//                        Toast.makeText(this, "Generated User Data failed #${i}. Please try again.", Toast.LENGTH_SHORT).show()
//                    }
//            }
//            var listings = DataHelper.initializeListingData()
//            var i = 0
//            for(listing in listings) {
//                FirebaseDatabase.getInstance().getReference().child("rooms").push()
//                    .setValue(listing)
//                    .addOnSuccessListener {
//                        // The data was inserted successfully
//                        Toast.makeText(this, "Generated Listing Data #${i} successful!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener {
//                        // The data insertion failed
//                        Toast.makeText(this, "Generated Listing Data failed #${i}. Please try again.", Toast.LENGTH_SHORT).show()
//                    }
//                i++
//            }
//
        }
    }
    // Function to create a user session using SharedPreferences
    fun createSession(uid: String) {
        val prefs = getSharedPreferences("APP_SESSION", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("uid", "")
        editor.putString("uid", uid)
        editor.apply()

        Log.d("UID--------------------------------------------", uid)
    }
}