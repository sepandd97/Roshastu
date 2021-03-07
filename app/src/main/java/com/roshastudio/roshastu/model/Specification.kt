package com.roshastudio.roshastu.model

import android.os.Parcel
import android.os.Parcelable

data class Specification(val id:Int,val type:String?,val desc:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(type)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Specification> {
        override fun createFromParcel(parcel: Parcel): Specification {
            return Specification(parcel)
        }

        override fun newArray(size: Int): Array<Specification?> {
            return arrayOfNulls(size)
        }
    }
}