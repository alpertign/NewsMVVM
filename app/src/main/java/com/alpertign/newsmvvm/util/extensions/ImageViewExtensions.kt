package com.alpertign.newsmvvm.util.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.alpertign.newsmvvm.R

/**
 * Created by Alperen Acikgoz on 09,August,2023
 */

@BindingAdapter(
    value = ["app:imageUrl", "app:placeholder", "app:error", "app:crossFade"],
    requireAll = false
)
fun ImageView.bindImageUrl(
    imageUrl: String?,
    placeholder: Drawable? = null,
    error: Drawable? = null,
    crossFade: Boolean = true,
) {


    val loadImageUrl = imageUrl ?: "alpertign rocks"

    try {
        load(loadImageUrl) {
            crossfade(crossFade)
            error?.run {
                error(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_ball_24,
                        this@bindImageUrl.context.theme
                    )
                )
            }
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