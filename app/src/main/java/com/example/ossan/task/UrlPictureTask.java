package com.example.ossan.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.ossan.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlPictureTask extends AsyncTask<Object, Void, Bitmap> {
    private final static String TAG = "UrlPictureTask";
    private String url;

    private WeakReference<ImageView> imageViewWeakReference;


// 取單張圖片
    //public UrlPictureTask(String url, int id, int imageSize) {
//        this(url, id, imageSize, null);
//    }

    public UrlPictureTask(String url, ImageView imageView){
        this.url = url;
        this.imageViewWeakReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(Object... objects) {
        return getRemoteImage(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewWeakReference.get();
        if (isCancelled() || imageView == null) {
            return;
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.no_image);
        }
    }

    private Bitmap getRemoteImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
