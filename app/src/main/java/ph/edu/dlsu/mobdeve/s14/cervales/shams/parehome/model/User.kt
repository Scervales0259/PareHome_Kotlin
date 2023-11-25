package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model

data class User (
    var firstName: String,
    var lastName: String,
    var phone: String,
    var description: String,
    var username: String,
    var password: String,
    var profilePicture: String,
    var fullAddress: String,
    var country: String,
    var region: String,
    var city: String,
    var street: String,
    var dateJoined: String
){
}