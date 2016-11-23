package com.exemple.android.myapplication.retrofit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetBitmap extends AsyncTask<String, Void, Bitmap> {

private AvatarProcessed avatarProcessedListener;
    private WeakReference<Context> weakContext;

    public GetBitmap(Context context, AvatarProcessed avatarProcessedListener) {
        weakContext = new WeakReference<>(context);
        this.avatarProcessedListener = avatarProcessedListener;
    }
    @Override
    protected Bitmap doInBackground(String... src) {

    return getBitmapFromUrl(src[0]);
    }
    public Bitmap getBitmapFromUrl(String src) {
        Bitmap myBitmap = null;
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myBitmap;
    }
    @Override
    protected void onPostExecute(Bitmap myBitmap) {
        super.onPostExecute(myBitmap);
        avatarProcessedListener.onBitmapReady(myBitmap);
    }
    public interface AvatarProcessed {
        void onBitmapReady(Bitmap myBitmap);
    }
}
