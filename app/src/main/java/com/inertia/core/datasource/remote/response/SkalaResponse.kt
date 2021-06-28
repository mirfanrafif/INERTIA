package com.inertia.core.response

import com.google.gson.annotations.SerializedName

data class SkalaResponse(
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("namaSkala")
	val nmSkala: String,

	@field:SerializedName("value")
	val value: String,
)
