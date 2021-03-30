package com.sekar.modal

import java.io.Serializable

data class Photo(
 var albumId:String,
 var id:String,
 var title:String,
 var url:String,
 var thumbnailUrl:String
):Serializable
