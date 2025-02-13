package com.inertia.core.datasource.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bencanaEntity")
data class BencanaEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idbencana")
    var id: String,

    @ColumnInfo(name = "namaBencana")
    var namaBencana: String?,
    //jenis bencana di generate aja. tinggal get
    @ColumnInfo(name = "jenisBencana")
    var jenisBencana: String?,

    @ColumnInfo(name = "kronologiBencana")
    var kronologiBencana: String?,

    @ColumnInfo(name = "longitude")
    var longitude: Double?,

    @ColumnInfo(name = "latitude")
    var latitude: Double?,

    @ColumnInfo(name = "waktu_aduan")
    var waktuAduan: String?,

    @ColumnInfo(name = "waktu_bencana")
    var waktuBencana: String?,

    @ColumnInfo(name = "linkFoto")
    var linkFoto: String?,

    @ColumnInfo(name = "sender_wa_number")
    var nomorWaPengadu: String?,

    @ColumnInfo(name = "kota")
    var kota: String? = null,

    @ColumnInfo(name = "provinsi")
    var provinsi: String? = null,

    @ColumnInfo(name = "uri_donasi")
    var uriDonasi: String?,
) : Parcelable