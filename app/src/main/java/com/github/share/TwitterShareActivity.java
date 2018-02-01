package com.github.share;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.share.utils.AppUtils;

import java.io.File;

import static com.github.share.R.mipmap.share;

/**
 * Created by zlove on 2018/2/1.
 */

public class TwitterShareActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TWITTER_PACKAGE_NAME = "com.twitter.android";

    private Button btnShareImg;
    private Button btnShareVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_share);

        btnShareImg = findViewById(R.id.share_img);
        btnShareVideo = findViewById(R.id.share_video);

        btnShareImg.setOnClickListener(this);
        btnShareVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnShareImg) {
            shareImg();
        } else if (view == btnShareVideo) {
            shareVideo();
        }
    }

    private void shareImg() {
        if (!AppUtils.isAppInstalled(TWITTER_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装Instagram...", Toast.LENGTH_SHORT).show();
        }
        String type = "image/*";
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(share) + "/"
                + getResources().getResourceTypeName(share) + "/"
                + getResources().getResourceEntryName(share));

        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);
        share.putExtra(Intent.EXTRA_TEXT, "分享说明");

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage(TWITTER_PACKAGE_NAME);
        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }

    private void shareVideo() {
        if (!AppUtils.isAppInstalled(TWITTER_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装Instagram...", Toast.LENGTH_SHORT).show();
        }
        String type = "video/*";
        String mediaPath = "/storage/emulated/0/相机/729d2e15-0204-4e14-95a6-086436aa805b.mp4";
                // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);
        share.putExtra(Intent.EXTRA_TEXT, "分享说明");
        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);
        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage(TWITTER_PACKAGE_NAME);
        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }
}
