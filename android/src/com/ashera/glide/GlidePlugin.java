package com.ashera.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.ashera.image.ITarget;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class GlidePlugin  {
    private static final class GlideTargetImageDownloader implements com.ashera.image.IImageDownloader {
        @Override
        public void download(Object context, String url, Object placeholder, Object error, final ITarget target) {
            Context mycontext = (Context) context;
            Glide.with(mycontext).asBitmap()
                    .load(url)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            target.onBitmapLoaded(resource);
                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            target.onBitmapFailed(errorDrawable);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            target.onPrepareLoad(placeholder);
                        }
                    });
        }
    }

    public static void initPlugin() {
        // start - body
        // end - body
        com.ashera.image.ImageDownloaderFactory.register(new GlidePlugin.GlideTargetImageDownloader());
    }
}
