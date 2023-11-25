package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityUploadListingBinding
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.Listing

class UploadListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadListingBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Upload button click listener
        binding.activityUploadListingBtnUpload.setOnClickListener {
            insertData()
        }
    }

    // Function to insert listing data into Firebase Realtime Database
    @RequiresApi(Build.VERSION_CODES.O)
    fun insertData() {

        var listing = Listing(
            binding.activityUploadListingEtFirstName.text.toString(),
            binding.activityUploadListingEtLastName.text.toString(),
            binding.activityUploadListingEtnPhoneNumber.text.toString(),
            binding.activityUploadListingEtEmailAddress.text.toString(),
            binding.activityUploadListingEtApartmentName.text.toString(),
            binding.activityUploadListingEtRoomNumber.text.toString(),
            binding.activityUploadListingEtTotalOccupancy.text.toString(),
            binding.activityUploadListingEtTotalBedrooms.text.toString(),
            binding.activityUploadListingEtTotalBathrooms.text.toString(),
            binding.activityUploadListingTvDescriptionListing.text.toString(),
            binding.activityUploadListingEtExactAddress.text.toString(),
            binding.activityUploadListingEtCountry.text.toString(),
            binding.activityUploadListingEtRegion.text.toString(),
            binding.activityUploadListingEtCity.text.toString(),
            binding.activityUploadListingEtPostalCode.text.toString(),
            binding.activityUploadListingEtStreet.text.toString(),
            binding.activityUploadListingEtPricePerMonth.text.toString(),
            getSessionID().toString(),
            binding.activityUploadListingEtRoomPicture1.text.toString(),
            binding.activityUploadListingEtRoomPicture2.text.toString(),
            binding.activityUploadListingEtRoomPicture3.text.toString(),
            binding.activityUploadListingEtRoomPicture4.text.toString(),
            binding.activityUploadListingEtRoomPicture5.text.toString(),
            binding.activityUploadListingEtLongitude.text.toString(),
            binding.activityUploadListingEtLatitude.text.toString())
        // Push listing data to "rooms" node in the Firebase Realtime Database
        FirebaseDatabase.getInstance().getReference().child("rooms").push()
            .setValue(listing)
            .addOnSuccessListener {
                // The data was inserted successfully
                Toast.makeText(this, "Listing upload successful!", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
                clearData()
            }
            .addOnFailureListener {
                // The data insertion failed
                Toast.makeText(this, "Listing upload failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
    }
    // Function to clear all input fields after successful upload
    fun clearData() {
        binding.activityUploadListingEtFirstName.text.clear()
        binding.activityUploadListingEtLastName.text.clear()
        binding.activityUploadListingEtnPhoneNumber.text.clear()
        binding.activityUploadListingEtEmailAddress.text.clear()
        binding.activityUploadListingEtApartmentName.text.clear()
        binding.activityUploadListingEtRoomNumber.text.clear()
        binding.activityUploadListingEtTotalOccupancy.text.clear()
        binding.activityUploadListingEtTotalBedrooms.text.clear()
        binding.activityUploadListingEtTotalBathrooms.text.clear()
        binding.activityUploadListingEtDescriptionListing.text.clear()
        binding.activityUploadListingEtCountry.text.clear()
        binding.activityUploadListingEtCity.text.clear()
        binding.activityUploadListingEtExactAddress.text.clear()
        binding.activityUploadListingEtPostalCode.text.clear()
        binding.activityUploadListingEtPricePerMonth.text.clear()
        binding.activityUploadListingEtRoomPicture1.text.clear()
        binding.activityUploadListingEtRoomPicture2.text.clear()
        binding.activityUploadListingEtRoomPicture3.text.clear()
        binding.activityUploadListingEtRoomPicture4.text.clear()
        binding.activityUploadListingEtRoomPicture5.text.clear()
        binding.activityUploadListingEtLongitude.text.clear()
        binding.activityUploadListingEtLatitude.text.clear()
    }
    // Function to get the current user's session ID
    fun getSessionID() : String? {
        var returnValue = "null"
        val prefs = getSharedPreferences("APP_SESSION", MODE_PRIVATE)
        returnValue = prefs.getString("uid", "null").toString()
        return returnValue
    }
}