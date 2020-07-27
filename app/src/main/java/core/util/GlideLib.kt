package core.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

class GlideLib {

    companion object {

        private val options = RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)

        fun setImage(context: Context, resId: Int, imageView: ImageView) {
            Glide.with(context)
                    .load(resId)
                    .transition(withCrossFade())
                    .apply(options)
                    .into(imageView)
        }

        fun setImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(options)
                    .into(imageView)
        }

    }

}