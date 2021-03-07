package com.roshastudio.roshastu.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

data class ProductSpecification(val id:Int,val title:String?,val specification:ArrayList<Specification>?):Parcelable  {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.createTypedArrayList(Specification)
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as ProductSpecification
        if (id != other.id) {
            return false
        }
        if (title != other.title) {
            return false
        }
        if (specification != other.specification) {
            return false
        }

        return true
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeTypedList(specification)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductSpecification> {
        override fun createFromParcel(parcel: Parcel): ProductSpecification {
            return ProductSpecification(parcel)
        }

        override fun newArray(size: Int): Array<ProductSpecification?> {
            return arrayOfNulls(size)
        }
    }

}