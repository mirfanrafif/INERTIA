package com.inertia.core.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class LaporResponse(

	@field:SerializedName("address")
	val address: Address? = null,

	@field:SerializedName("bencana")
	val bencana: Bencana? = null
)

data class Address(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("postcode")
	val postcode: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("village")
	val village: String? = null
)

data class Bencana(

	@field:SerializedName("id_aduan")
	val idAduan: String,

	@field:SerializedName("kronologi")
	val kronologi: String? = null,

	@field:SerializedName("lat_long")
	val latLong: String? = null,

	@field:SerializedName("sender_wa_number")
	val senderWaNumber: String? = null,

	@field:SerializedName("gambar_uri")
	val gambarUri: String? = null,

	@field:SerializedName("jenis_bencana")
	val jenisBencana: String? = null,

	@field:SerializedName("waktu_bencana")
	val waktuBencana: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null
)
