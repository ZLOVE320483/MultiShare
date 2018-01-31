package com.github.share;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;

/**
 * Created by zlove on 2018/1/31.
 */

public class FacebookShareActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String VIDEO_PATH = "/storage/emulated/0/相机/729d2e15-0204-4e14-95a6-086436aa805b.mp4";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };

    private Button btnShareLink;
    private Button btnSharePhoto;
    private Button btnShareVideo;

    private ShareDialog shareDialog;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_share);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.d("ScreenActionReceiver", "---onSuccess---");
            }

            @Override
            public void onCancel() {
                Log.d("ScreenActionReceiver", "---onCancel---");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("ScreenActionReceiver", "---onError---" + error.toString());
            }
        });

        btnShareLink = findViewById(R.id.share_link);
        btnSharePhoto = findViewById(R.id.share_photo);
        btnShareVideo = findViewById(R.id.share_video);

        btnShareLink.setOnClickListener(this);
        btnSharePhoto.setOnClickListener(this);
        btnShareVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnShareLink) {
            shareLink();
        } else if (view == btnSharePhoto) {
            sharePhoto();
        } else if (view == btnShareVideo) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                verifyStoragePermissions(this);
            } else {
                shareVideo();
            }
        }
    }

    private void shareLink() {
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle("Share Test")
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();

        shareDialog.show(linkContent);
    }

    private void sharePhoto() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.share);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        SharePhotoContent photoContent = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        shareDialog.show(photoContent);
    }

    private void shareVideo() {
        Uri videoFileUri = Uri.fromFile(new File(VIDEO_PATH));
        ShareVideo video = new ShareVideo.Builder()
                .setLocalUrl(videoFileUri)
                .build();
        ShareVideoContent content = new ShareVideoContent.Builder()
                .setVideo(video)
                .build();

        shareDialog.show(content);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            } else {
                shareVideo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
