package com.example.gettingpictures;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
Button button,gallery;
private static final int CAMERA_REQ_CODE=100;
    private static final int GALLERY_REQ_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image);
        button=findViewById(R.id.btn);
        gallery=findViewById(R.id.btn2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera=new Intent();
                iCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(iCamera,CAMERA_REQ_CODE);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery=new Intent();
                iGallery.setAction(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==CAMERA_REQ_CODE){
                //For Camera
                Bitmap bitmap=(Bitmap)(data.getExtras().get("data"));
                imageView.setImageBitmap(bitmap);
            }
            else if (requestCode==GALLERY_REQ_CODE) {
                imageView.setImageURI(data.getData());
            }
        }
    }
}