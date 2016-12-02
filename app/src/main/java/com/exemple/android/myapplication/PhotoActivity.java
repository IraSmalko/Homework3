package com.exemple.android.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class PhotoActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private final int CAMERA_RESULT = 0;
    Bitmap bitmap = null;
    ImageView imageView;
    Bitmap restoreBitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            restoreBitmap = savedInstanceState.getParcelable("bitmap");
            imageView.setImageBitmap(restoreBitmap);
        } else {
            setContentView(R.layout.photo_activity_view);
            Button photo_fromGallery = (Button) findViewById(R.id.photo_fromGallery);
            photo_fromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                }
            });
            Button photo_fromCamera = (Button) findViewById(R.id.photo_fromCamera);
            photo_fromCamera.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_RESULT);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        imageView = (ImageView) findViewById(R.id.photo_imageView);

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
            case CAMERA_RESULT:
                if (requestCode == CAMERA_RESULT) {
                    Bitmap thumbnailBitmap = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    imageView.setImageBitmap(thumbnailBitmap);
                }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("bitmap", bitmap);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Bitmap restoreBitmap = savedInstanceState.getParcelable("bitmap");
        imageView.setImageBitmap(restoreBitmap);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

}