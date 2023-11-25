package ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome

import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.Listing
import ph.edu.dlsu.mobdeve.s14.cervales.shams.parehome.model.User

class DataHelper {
    companion object {
        /*
        * The initializeUserData function is defined to create and return a list of User objects.
        * It creates instances of the User class with predefined data and adds them to the users list.
        * */
        fun initializeUserData(): ArrayList<User> {
            var users = ArrayList<User>()

            users.add(
                User(
                    "maria",
                    "cruz",
                    "9215584712",
                    "I'm a teacher",
                    "mcruz",
                    "password",
                    "https://i.ibb.co/Xthm0dj/2022-11-26-T05-04-55-271-Z2.jpg",
                    "1521 P. Burgos Street",
                    "Philippines",
                    "Metro Manila",
                    "Makati",
                    "P. Burgos Street",
                    "March 12, 2022",
                )
            )

            users.add(
                User(
                    "juan",
                    "dela cruz",
                    "9263124578",
                    "I'm a doctor",
                    "jdelacruz",
                    "password",
                    "https://i.ibb.co/KsbtpwV/2022-11-26-T05-27-00-346-Z1.jpg",
                    "2345 Quezon Avenue",
                    "Philippines",
                    "Metro Manila",
                    "Quezon City",
                    "Quezon Avenue",
                    "April 20, 2023",
                )
            )

            users.add(
                User(
                    "Carlos",
                    "gonzalez",
                    "9272345678",
                    "I'm a nurse",
                    "agonzalez",
                    "password",
                    "https://i.ibb.co/G0SW350/2022-11-26-T05-31-42-968-Z3.png",
                    "5678 Taft Avenue",
                    "Philippines",
                    "Metro Manila",
                    "Pasay",
                    "Taft Avenue",
                    "May 30, 2024",
                )
            )

            users.add(
                User(
                    "pedro",
                    "sanchez",
                    "9281234567",
                    "I'm an engineer",
                    "psanchez",
                    "password",
                    "https://i.ibb.co/35XSCK2/2022-11-26-T05-32-35-605-Z4.jpg",
                    "8901 EDSA",
                    "Philippines",
                    "Metro Manila",
                    "Mandaluyong",
                    "EDSA",
                    "June 28, 2025",
                )
            )

            users.add(
                User(
                    "jose",
                    "garcia",
                    "9290123456",
                    "I'm a lawyer",
                    "jgarcia",
                    "password",
                    "https://i.ibb.co/GT7fZkt/2022-11-26-T05-34-54-274-Z5.jpg",
                    "1234 Kalayaan Avenue",
                    "Philippines",
                    "Metro Manila",
                    "Makati",
                    "Kalayaan Avenue",
                    "July 26, 2026",
                )
            )
            return users
        }

        /*
        * The initializeListingData function is similar to initializeUserData but creates and returns a list of Listing objects with predefined data.
        * */
        fun initializeListingData(): ArrayList<Listing> {
            var listings = ArrayList<Listing>()

            listings.add(
                Listing(
                    "Juan",
                    "Dela Cruz",
                    "9177019757",
                    "j.delacruz@gmail.com",
                    "Dela Cruz Residence",
                    "3",
                    "2",
                    "1",
                    "2",
                    "A spacious and modern apartment located in the heart of Pasay. This unit is perfect for couples or small families.",
                    "123 Dela Cruz Street, Pasay City",
                    "Philippines",
                    "Metro Manila",
                    "Pasay City",
                    "1500",
                    "Dela Cruz St",
                    "18000",
                    "-Nk0f9jB7UOi9TTXpfq6",
                    "https://i.ibb.co/d0sQ3PG/2022-11-26-T05-39-18-721-Z1.webp",
                    "https://i.ibb.co/T0Qy8zy/2022-11-26-T05-39-18-726-Z2.webp",
                    "https://i.ibb.co/W6XZ9J6/2022-11-26-T05-39-18-727-Z3.jpg",
                    "https://i.ibb.co/XZGrC19/2022-11-26-T05-39-18-728-Z4.webp",
                    "https://i.ibb.co/8zqc90x/2022-11-26-T05-39-18-728-Z5.webp",
                    "14.54674446899136",
                    "121.00733923877429"
                )
            )

            listings.add(
                Listing(
                    "Maria",
                    "Lopez",
                    "9988776655",
                    "m.lopez@gmail.com",
                    "City View Condo",
                    "2",
                    "1",
                    "1",
                    "1",
                    "Enjoy the city lights from this stylish condo. Ideal for couples or business travelers. Close to shopping malls, restaurants, and public transportation. Experience urban living at its best.",
                    "123 Ayala Avenue",
                    "Philippines",
                    "Metro Manila",
                    "Makati",
                    "1223",
                    "Metro Ave",
                    "18000",
                    "-Nk0f9jHUNHeG4u91STT",
                    "https://i.ibb.co/FWPT6nn/2022-11-26-T05-43-05-277-Z1.webp",
                    "https://i.ibb.co/nDYQfnV/2022-11-26-T05-43-05-278-Z2.webp",
                    "https://i.ibb.co/RHTwXMy/2022-11-26-T05-43-05-281-Z3.webp",
                    "https://i.ibb.co/bz5tnxk/2022-11-26-T05-43-05-284-Z4.webp",
                    "https://i.ibb.co/9bjxLy8/2022-11-26-T05-43-05-285-Z5.webp",
                    "14.556700996924919",
                    "121.02178238110372"
                )
            )

            listings.add(
                Listing(
                    "Carlos",
                    "Garcia",
                    "9333444555",
                    "c.garcia@gmail.com",
                    "Beachfront Bungalow",
                    "3",
                    "2",
                    "2",
                    "1",
                    "Wake up to the sound of waves in this beachfront bungalow. Perfect for a relaxing getaway. Enjoy the white sandy beach, snorkeling, and stunning sunsets. Just steps away from local seafood restaurants.",
                    "456 Seaside Drive",
                    "Philippines",
                    "Cebu",
                    "Mactan",
                    "6015",
                    "Seaside Dr",
                    "22000",
                    "-Nk0f9jIX4e2UPiq68zp",
                    "https://i.ibb.co/kqvMWYY/2022-11-26-T05-46-09-033-Z1.webp",
                    "https://i.ibb.co/tXm67yD/2022-11-26-T05-46-09-034-Z2.webp",
                    "https://i.ibb.co/k6QdKBN/2022-11-26-T05-46-09-038-Z3.webp",
                    "https://i.ibb.co/ZNQmt9x/2022-11-26-T05-46-09-044-Z4.webp",
                    "https://i.ibb.co/TTMsj1g/2022-11-26-T05-46-09-057-Z5.webp",
                    "10.324716431597011",
                    "123.88577329489094"
                )
            )

            listings.add(
                Listing(
                    "Isabel",
                    "Torres",
                    "9776655443",
                    "i.torres@gmail.com",
                    "Mountain View Cabin",
                    "2",
                    "1",
                    "1",
                    "1",
                    "Experience mountain living in this cozy cabin. Ideal for nature lovers and hikers. Enjoy panoramic views of lush forests and mountain peaks. A perfect retreat, yet only a short drive from the city.",
                    "789 Pine Ridge",
                    "Philippines",
                    "Baguio",
                    "Baguio City",
                    "2600",
                    "Pine Ridge",
                    "16000",
                    "-Nk0f9jJBwoKJDBZpfIV",
                    "https://i.ibb.co/QXvsqMY/2022-11-26-T05-48-47-512-Z1.webp",
                    "https://i.ibb.co/f9XgV5M/2022-11-26-T05-48-47-514-Z2.webp",
                    "https://i.ibb.co/khpzfVb/2022-11-26-T05-48-47-516-Z3.webp",
                    "https://i.ibb.co/yRWhWcK/2022-11-26-T05-48-47-519-Z4.webp",
                    "https://i.ibb.co/S7FbXRH/2022-11-26-T05-48-47-523-Z5.webp",
                    "16.37863886001684",
                    "120.57302125229025"
                )
            )

            listings.add(
                Listing(
                    "Ricardo",
                    "Reyes",
                    "9222333444",
                    "r.reyes@gmail.com",
                    "Historic Heritage House",
                    "4",
                    "3",
                    "2",
                    "1",
                    "Step back in time in this beautifully restored heritage house. Perfect for history enthusiasts. Enjoy antique furnishings, high ceilings, and a spacious courtyard. Located in the heart of the historic district.",
                    "101 Heritage Street",
                    "Philippines",
                    "Ilocos Norte",
                    "Vigan",
                    "2700",
                    "Heritage St",
                    "28000",
                    "-Nk0f9jJBwoKJDBZpfIW",
                    "https://i.ibb.co/rFnF32g/2022-11-26-T05-51-18-516-Z1.webp",
                    "https://i.ibb.co/pnW1wmZ/2022-11-26-T05-51-18-521-Z2.webp",
                    "https://i.ibb.co/BcFk9QM/2022-11-26-T05-51-18-524-Z3.webp",
                    "https://i.ibb.co/HNwX3yv/2022-11-26-T05-51-18-527-Z4.webp",
                    "https://i.ibb.co/ScJv8jp/2022-11-26-T05-51-18-530-Z5.webp",
                    "16.21735784546913",
                    "120.64340387762421"
                )
            )
            return listings
        }
    }
}