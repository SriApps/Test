package com.test.sri.test.Model

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by Myworld on 21/04/2018.
 */

data class FlickrDataModel(
		val title: String,
		val link: String,
		val description: String,
		val modified: String,
		val generator: String,
		val items: List<Item>
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			ArrayList<Item>().apply { source.readList(this, Item::class.java.classLoader) }
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(link)
        writeString(description)
        writeString(modified)
        writeString(generator)
        writeList(items)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<FlickrDataModel> = object : Parcelable.Creator<FlickrDataModel> {
			override fun createFromParcel(source: Parcel): FlickrDataModel = FlickrDataModel(source)
			override fun newArray(size: Int): Array<FlickrDataModel?> = arrayOfNulls(size)
		}
	}
}

data class Item(
        val title: String,
        val link: String,
        val media: Media,
        val date_taken: String,
        val description: String,
        val published: String,
        val author: String,
        val author_id: String,
        val tags: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            TODO("media"),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeString(date_taken)
        parcel.writeString(description)
        parcel.writeString(published)
        parcel.writeString(author)
        parcel.writeString(author_id)
        parcel.writeString(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}

data class Media(
		val m: String
)