package com.edu.kotlinnews.model

/**
 *
 *
 * Define data structure
 *
 *
 */
data class ListItem(var kind: String, var data: Data)

data class Data(
    var selftext: String?,
    var title: String,
    var thumbnail: String?,
    var url: String
)