package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityMainBinding
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.Listing

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listingAdapter: ListingAdapter

    // Main activity displaying listings and providing navigation options
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView layout manager and adapter for displaying listings
        binding.topPicksRv.layoutManager = LinearLayoutManager(this)

        // FirebaseRecyclerOptions for configuring the ListingAdapter
        var options: FirebaseRecyclerOptions<Listing> = FirebaseRecyclerOptions.Builder<Listing>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("rooms"), Listing::class.java)
            .build()

        // Initialize ListingAdapter with FirebaseRecyclerOptions
        listingAdapter = ListingAdapter(options)
        binding.topPicksRv.adapter = listingAdapter

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

        // Set item click listener for ListingAdapter
        listingAdapter.setOnItemClickListener(object: ListingAdapter.onItemClickListener{
            override fun onItemClick(position: Int, type: String) {
                var listing = listingAdapter.listings[position]
                if(type.equals("room")) {
                    val intent = Intent(this@MainActivity, ListingActivity::class.java)
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

                    intent.putExtras(bundle)
                    startActivity(intent)

                } else if(type.equals("user")) {
                    var uid = listing.ownerId
                    val reference = FirebaseDatabase.getInstance().getReference("users")
                    val userReference = reference.child(uid)

                    userReference!!.get().addOnSuccessListener { dataSnapshot ->
                        // Check if the user exists
                        if (dataSnapshot.exists()) {
                            // Extract user data from the snapshot
                            val userData = dataSnapshot.value as Map<String, Any>?
                            val intent = Intent(this@MainActivity, OtherProfileActivity::class.java)
                            val bundle = Bundle()

                            bundle.putString("firstName", userData!!["firstName"] as String?)
                            bundle.putString("lastName", userData!!["lastName"] as String?)
                            bundle.putString("city", userData!!["city"] as String?)
                            bundle.putString("dateJoined", userData!!["dateJoined"] as String?)
                            bundle.putString("profilePicture", userData!!["profilePicture"] as String?)

                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    }
                }
            }
        })
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

