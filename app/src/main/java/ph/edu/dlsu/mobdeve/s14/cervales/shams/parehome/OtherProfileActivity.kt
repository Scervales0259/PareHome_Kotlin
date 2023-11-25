package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityOtherProfileBinding
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.Listing

class OtherProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtherProfileBinding
    private lateinit var listingAdapter: ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherProfileBinding.inflate(layoutInflater)
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
                var listing = listingAdapter.listings[position]
                // Handle item click based on type (room or user)
                if(type.equals("room")) {
                    val intent = Intent(this@OtherProfileActivity, ListingActivity::class.java)
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

                    // Handle click for user listing
                } else if(type.equals("user")) {
                    var uid = listing.ownerId
                    val reference = FirebaseDatabase.getInstance().getReference("users")
                    val userReference = reference.child(uid)

                    userReference!!.get().addOnSuccessListener { dataSnapshot ->
                        // Check if the user exists
                        if (dataSnapshot.exists()) {
                            // Extract user data from the snapshot
                            val userData = dataSnapshot.value as Map<String, Any>?
                            val intent = Intent(this@OtherProfileActivity, OtherProfileActivity::class.java)
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
            val intent = Intent(this, OtherProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Data of the Other user
        val bundle = intent.extras
        binding.activityOtherProfileTvFullName.text = bundle!!.getString("firstName").toString() + " " + bundle!!.getString("lastName").toString()
        binding.activityOtherProfileTvCity.text = bundle!!.getString("city").toString()
        binding.activityOtherProfileTvDateJoined.text = bundle!!.getString("dateJoined").toString()

        Glide.with(binding.activityOtherProfileIvProfilePicture.context)
            .load(bundle!!.getString("profilePicture").toString())
            .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
            .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
            .into(binding.activityOtherProfileIvProfilePicture)
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