package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityListingBinding
import android.webkit.WebView;


class ListingActivity : AppCompatActivity() {
    // Binding for the layout of the activity
    private lateinit var binding: ActivityListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflating the layout using ViewBinding
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieving data passed from the calling activity
        val bundle = intent.extras

        // Setting the title and location information in the UI
        binding.activityListingTvTitle.text = bundle!!.getString("name")
        binding.activityListingTvLocation.text = bundle!!.getString("addressCity") +", "+
                                                bundle!!.getString("addressRegion")+", "+
                                                bundle!!.getString("addressCountry")

        // Loading image using Glide library
        Glide.with(binding.activityListingIvImage.context)
            .load(bundle!!.getString("picture1"))
            .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
            .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
            .into(binding.activityListingIvImage)

        // Setting various property details in the UI
        binding.activityListingTvTotalOccupancy.text = bundle!!.getString("totalOccupancy") + " Guest/s"
        binding.activityListingTvTotalBedrooms.text = bundle!!.getString("totalBedrooms") + " Bed/s"
        binding.activityListingTvTotalBathrooms.text = bundle!!.getString("totalBathrooms") + " Bed/s"
        binding.activityListingTvPricePerMonth.text = "Php "+ bundle!!.getString("price") + "/Month"
        binding.activityListingTvDescriptionListing.text = bundle!!.getString("description")

        // Setting up WebView for displaying Google Maps
        val webSettings: WebSettings = binding.activityListingWebview.getSettings()
        webSettings.javaScriptEnabled = true

        // Extracting latitude and longitude to display Google Map in WebView
        var longitude = bundle!!.getString("longitude")
        var latitude = bundle!!.getString("latitude")

        binding.activityListingWebview.loadData("<iframe width=\"100%\" height=\"500\" src=\"https://maps.google.com/maps?q=${longitude}, ${latitude}&z=15&output=embed\"></iframe>", "text/html", "UTF-8");

        // Fetching owner information from Firebase Realtime Database
        val reference = FirebaseDatabase.getInstance().getReference("users")
        val userReference = reference.child(bundle!!.getString("ownerId").toString())

        userReference!!.get().addOnSuccessListener { dataSnapshot ->
            // Check if the user exists
            if (dataSnapshot.exists()) {
                // Extract user data from the snapshot
                val userData = dataSnapshot.value as Map<String, Any>?

                binding.activityListingTvOwnerName.text = "Hosted by "+userData!!["lastName"] as String?

                Glide.with(binding.activityListingIvProfilePicture.context)
                    .load(userData!!["profilePicture"] as String?)
                    .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                    .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(binding.activityListingIvProfilePicture)
            }
        }

        // Handling the button click to check the owner's profile
        binding.activityListingBtnCheckProfile.setOnClickListener{
            val reference = FirebaseDatabase.getInstance().getReference("users")
            val userReference = reference.child(bundle!!.getString("ownerId").toString())

            userReference!!.get().addOnSuccessListener { dataSnapshot ->
                // Check if the user exists
                if (dataSnapshot.exists()) {
                    // Extract user data from the snapshot
                    val userData = dataSnapshot.value as Map<String, Any>?

                    // Creating an Intent to navigate to the OtherProfileActivity
                    val intent = Intent(this@ListingActivity, OtherProfileActivity::class.java)
                    val bundle1 = Bundle()

                    // Adding user details to the bundle for OtherProfileActivity
                    bundle1.putString("firstName", userData!!["firstName"] as String?)
                    bundle1.putString("lastName",  userData!!["lastName"] as String?)
                    bundle1.putString("city",  userData!!["city"] as String?)
                    bundle1.putString("dateJoined",  userData!!["dateJoined"] as String?)
                    bundle1.putString("profilePicture",  userData!!["profilePicture"] as String?)
                    bundle1.putString("ownerId", bundle!!.getString("ownerId").toString())

                    // Adding the bundle to the intent and starting the OtherProfileActivity
                    intent.putExtras(bundle1)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}