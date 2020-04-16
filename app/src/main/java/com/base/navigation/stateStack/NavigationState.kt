package com.base.navigation.stateStack

import android.os.Parcel
import android.os.Parcelable

/**
 * @author ThucPT on 3/15/2018.
 */

data class NavigationState(val placeholder: Int, val backStackName: String = (Integer.MAX_VALUE * Math.random()).toString()) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(placeholder)
        parcel.writeString(backStackName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NavigationState> {
        override fun createFromParcel(parcel: Parcel): NavigationState {
            return NavigationState(parcel)
        }

        override fun newArray(size: Int): Array<NavigationState?> {
            return arrayOfNulls(size)
        }
    }


}
