package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityProfileBinding
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.Listing

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var listingAdapter: ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set up RecyclerView layout manager and adapter for displaying listings
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // FirebaseRecyclerOptions for configuring the ListingAdapter
        var options: FirebaseRecyclerOptions<Listing> = FirebaseRecyclerOptions.Builder<Listing>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("rooms"), Listing::class.java)
            .build()

        // Initialize ListingAdapter with FirebaseRecyclerOptions
        listingAdapter = ListingAdapter(options)
        binding.recyclerView.adapter = listingAdapter

        // Set item click listener for ListingAdapter
        listingAdapter.setOnItemClickListener(object: ListingAdapter.onItemClickListener{
            override fun onItemClick(position: Int, type: String) {
                // Handle item click based on type (room or user)
                var listing = listingAdapter.listings[position]
                if(type.equals("room")) {
                    // Handle click for room listing
                    val intent = Intent(this@ProfileActivity, ListingActivity::class.java)
                    val bundle = Bundle()

                    bundle.putString("firstName", listing.firstName)
                    bundle.putString("lastName", listing.lastName)
                    bundle.putString("phoneNumber", listing.phoneNumber)
                    bundle.putString("emailAddress", listing.emailAddress)
                    bundle.putString("name", listing.name)
                    bundle.putString("roomNumber", listing.roomNumber)
                    bundle.putString("totalOccupancy", listing.totalOccupancy)
                    bundle.putString("totalBedrooms", listing.totalBedrooms)
                    bundle.putString("totalBathrooms", listing.totalBathrooms)
                    bundle.putString("description", listing.description)
                    bundle.putString("addressExactAddress", listing.addressExactAddress)
                    bundle.putString("addressCountry", listing.addressCountry)
                    bundle.putString("addressRegion", listing.addressRegion)
                    bundle.putString("addressCity", listing.addressCity)
                    bundle.putString("addressPostalCode", listing.addressPostalCode)
                    bundle.putString("addressStreet", listing.addressStreet)
                    bundle.putString("price", listing.price)
                    bundle.putString("ownerId", listing.ownerId)
                    bundle.putString("picture1", listing.picture1)
                    bundle.putString("picture2", listing.picture2)
                    bundle.putString("picture3", listing.picture3)
                    bundle.putString("picture4", listing.picture4)
                    bundle.putString("picture5", listing.picture5)

                    // ... (put necessary data into the bundle)
                    intent.putExtras(bundle)
                    startActivity(intent)

                } else if(type.equals("user")) {
                    // Handle click for user listing
                    var uid = listing.ownerId
                    val reference = FirebaseDatabase.getInstance().getReference("users")
                    val userReference = reference.child(uid)

                    userReference!!.get().addOnSuccessListener { dataSnapshot ->
                        // Check if the user exists
                        if (dataSnapshot.exists()) {
                            // Extract user data from the snapshot
                            val userData = dataSnapshot.value as Map<String, Any>?
                            val intent = Intent(this@ProfileActivity, OtherProfileActivity::class.java)
                            val bundle = Bundle()

                            bundle.putString("firstName", userData!!["firstName"] as String?)
                            bundle.putString("lastName", userData!!["lastName"] as String?)
                            bundle.putString("city", userData!!["city"] as String?)
                            bundle.putString("dateJoined", userData!!["dateJoined"] as String?)
                            bundle.putString("profilePicture", userData!!["profilePicture"] as String?)

                            // ... (put necessary user data into the bundle)
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    }
                }
            }
        })

        // Click listener for logout button
        binding.logout.setOnClickListener {
            deleteSession()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Click listeners for home, upload, and account containers
        binding.homeContainer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.uploadContainer.setOnClickListener {
            val intent = Intent(this, UploadListingActivity::class.java)
            startActivity(intent)
        }

        binding.accountContainer.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Fetch user data and display it in the profile view
        var uid = getSessionID();
        val reference = FirebaseDatabase.getInstance().getReference("users")
        val userReference = uid?.let { reference.child(it) }

        userReference!!.get().addOnSuccessListener { dataSnapshot ->
            // Check if the user exists
            if (dataSnapshot.exists()) {
                // Extract user data from the snapshot
                val userData = dataSnapshot.value as Map<String, Any>?

                binding.activityProfileTvFullName.text = userData!!["firstName"] as String? + " " + userData!!["lastName"] as String?
                binding.activityProfileTvCity.text = userData!!["city"] as String? + " City"
                binding.activityProfileTvDateJoined.text = userData!!["dateJoined"] as String?

                Glide.with(binding.activityProfileIvProfilePicture.context)
                    .load(userData!!["profilePicture"] as String?)
                    .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                    .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(binding.activityProfileIvProfilePicture)
            }
        }
    }

    // Function to retrieve the user's session ID
    fun getSessionID() : String? {
        var returnValue = "null"
        val prefs = getSharedPreferences("APP_SESSION", MODE_PRIVATE)
        returnValue = prefs.getString("uid", "null").toString()
        return returnValue
    }

    // Function to delete the user's session
    fun deleteSession() {
        val prefs = getSharedPreferences("APP_SESSION", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("uid", "null")
        editor.apply()
    }

    // Start listening for changes in the FirebaseRecyclerAdapter
    override fun onStart() {
        super.onStart()
        listingAdapter.startListening()
    }

    // Stop listening for changes in the FirebaseRecyclerAdapter
    override fun onStop() {
        super.onStop()
        listingAdapter.stopListening()
    }
}