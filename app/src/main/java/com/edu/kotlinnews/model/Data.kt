package com.edu.kotlinnews.model

data class ListItem(var kind:String,var data:Data)

data class Data(
    var selftext:String?,
    var title:String,
    var thumbnail:String?,
    var url:String


)