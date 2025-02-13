package com.inertia.core.datasource.remote.request

import java.io.File

data class BencanaRequest(
    var file: File,
    val judul: String,
    val kronologi: String,
    val nomor_wa: String,
    val waktu_bencana: String,
    val lat_long: String
)
