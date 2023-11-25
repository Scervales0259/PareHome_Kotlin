package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


    }

//    fun initData(): ArrayList<Listing>{
//        val listings = arrayListOf(
//            Listing(
//                "Emannuel",
//                "Kim",
//                "9177019756",
//                "e.kim@gmail.com",
//                "Weng House",
//                "2",
//                "1",
//                "1",
//                "641-647 Mabolo Street Sta. Cruz 1000",
//                "Mabolo St",
//                "Manila",
//                "Metro Manila",
//                "Philippines",
//                "1300",
//                "15000",
//                "null",
//                "https://a0.muscache.com/im/pictures/miso/Hosting-763161708133464720/original/541a485d-5da8-44d4-a75d-4a3e6c6489b6.jpeg?im_w=960",
//                "https://a0.muscache.com/im/pictures/miso/Hosting-763161708133464720/original/03730ce5-4e95-481a-a7e3-1454ac4fcc81.jpeg?im_w=720",
//                "https://a0.muscache.com/im/pictures/miso/Hosting-763161708133464720/original/da80c585-7528-4661-b2aa-32d062c2c0d4.jpeg?im_w=720",
//                "https://a0.muscache.com/im/pictures/miso/Hosting-763161708133464720/original/3ee77afd-233a-4c94-8fe2-13ec5cd16796.jpeg?im_w=720",
//                "https://a0.muscache.com/im/pictures/miso/Hosting-763161708133464720/original/05aee91e-921a-4e26-ac1b-0084d082452a.jpeg?im_w=720"
//            )
//        )
//        return listings
//    }
}