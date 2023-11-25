package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.databinding.ListingItemBinding
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.Listing

// Custom RecyclerView adapter for displaying listings
class ListingAdapter(options: FirebaseRecyclerOptions<Listing>) :
    FirebaseRecyclerAdapter<Listing, ListingAdapter.ViewHolder>(options) {

    private lateinit var mListener: onItemClickListener
    private lateinit var binding: ListingItemBinding

    // Listener for item click events
    var listings: ArrayList<Listing> = ArrayList()
    var oldListings: ArrayList<Listing> = ArrayList()

    // Interface for handling item click events
    interface onItemClickListener {
        fun onItemClick(position: Int, type: String)
    }

    // Setter method for setting the item click listener
    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    // Create a ViewHolder to represent the item view in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ListingItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding, mListener)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: ListingAdapter.ViewHolder, position: Int, model: Listing) {
        listings.add(model)
        oldListings.add(model)
        holder.title.text = model.name
        holder.owner.text = getOwnerName(model.ownerId)
        holder.price.text = "PHP "+ model.price
        holder.location.text = model.addressCity
        holder.bedroom.text = model.totalBedrooms
        holder.bathroom.text = model.totalBathrooms

        // Load image using Glide library
        Glide.with(holder.imageRoom.context)
            .load(model.picture1)
            .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
            .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
            .into(holder.imageRoom)
    }

    // ViewHolder class representing the individual items in the RecyclerView
    inner class ViewHolder(itemView : ListingItemBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView.root){
        var title : TextView = itemView.listingItemTvTitle
        var owner: TextView = itemView.listingItemTvOwner
        var price : TextView = itemView.listingItemTvPrice
        var location : TextView = itemView.listingItemTvLocation
        var imageRoom : ImageView = itemView.listingItemSivImage
        var bedroom: TextView = itemView.listingItemTvBedroom
        var bathroom: TextView = itemView.listingItemTvBathroom

        // Initialize the ViewHolder and set click listeners
        init {
            itemView.listingItemTvTitle.setOnClickListener {
                listener.onItemClick(adapterPosition, "room")
            }
            itemView.listingItemTvOwner.setOnClickListener {
                listener.onItemClick(adapterPosition, "user")
            }
        }
    }

    // Function to get the owner's name based on owner ID
    fun getOwnerName(ownerId: String): String {
        var fullName: String = ""

        // Firebase Realtime Database reference
        val reference = FirebaseDatabase.getInstance().getReference("users")
        val userReference = reference.child(ownerId)

        // Asynchronously fetch user data from the database
        userReference!!.get().addOnSuccessListener { dataSnapshot ->
            // Check if the user exists
            if (dataSnapshot.exists()) {
                // Extract user data from the snapshot
                var userData = dataSnapshot.value as Map<String, Any>?
                fullName = userData!!["firstName"] as String?+ " " + userData!!["lastName"] as String?
            }
        }

        return fullName
    }

    // This method is called when the user types in a search query
    fun filter(query: String) {

    }
}