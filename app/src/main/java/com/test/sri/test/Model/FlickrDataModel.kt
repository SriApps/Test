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
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readParcelable<Media>(Media::class.java.classLoader),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(link)
        writeParcelable(media, 0)
        writeString(date_taken)
        writeString(description)
        writeString(published)
        writeString(author)
        writeString(author_id)
        writeString(tags)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {
            override fun createFromParcel(source: Parcel): Item = Item(source)
            override fun newArray(size: Int): Array<Item?> = arrayOfNulls(size)
        }
    }
}

data class Media(
        val m: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(m)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Media> = object : Parcelable.Creator<Media> {
            override fun createFromParcel(source: Parcel): Media = Media(source)
            override fun newArray(size: Int): Array<Media?> = arrayOfNulls(size)
        }
    }
}