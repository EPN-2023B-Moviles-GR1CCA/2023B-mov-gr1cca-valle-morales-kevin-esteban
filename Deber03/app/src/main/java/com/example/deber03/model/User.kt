package com.example.deber03.model

import android.os.Parcel
import android.os.Parcelable

class User(
    var username: String? = null,
    var emailAddress: String? = null,
    var imageUrl: String? = null,
    var key: String? = null,
    var token: String? = null,
    var headline: String? = null,
    var location: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(emailAddress)
        parcel.writeString(imageUrl)
        parcel.writeString(key)
        parcel.writeString(token)
        parcel.writeString(headline)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
