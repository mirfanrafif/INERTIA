package com.inertia.core.response

import com.google.gson.annotations.SerializedName

data class JenisBencanaResponse(
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("nmBencana")
	val nmBencana: String,
)
