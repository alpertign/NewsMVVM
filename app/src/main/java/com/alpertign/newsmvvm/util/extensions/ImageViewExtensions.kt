package com.alpertign.newsmvvm.util.extensions

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.alpertign.newsmvvm.R

/**
 * Created by Alperen Acikgoz on 09,August,2023
 */

@BindingAdapter(
    value = ["app:imageUrl"],
    requireAll = false
)
fun ImageView.bindImageUrl(
    imageUrl: String?
) {


    val loadImageUrl = imageUrl ?: "alpertign rocks"

    try {
        load(loadImageUrl) {
            crossfade(true)

            error(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_ball_24,
                    this@bindImageUrl.context.theme
                )
            )

            placeholder(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_ball_24,
                    this@bindImageUrl.context.theme
                )
            )
            bitmapConfig(Bitmap.Config.ARGB_8888)
            build()
        }
    } catch (e: Exception) {
        e.message
    }


}