package com.el_nico.cappasitytesttask.models.networking

import com.google.gson.annotations.SerializedName

class ImageLinkResponse(
    @SerializedName("items") var items: Array<ImageItem>?
)