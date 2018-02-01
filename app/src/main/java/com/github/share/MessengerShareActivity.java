package com.github.share;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateElement;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.widget.MessageDialog;

/**
 * Created by zlove on 2018/2/1.
 */

public class MessengerShareActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShareLink;
    private Button btnSharePhoto;
    private Button btnShareVideo;

    private CallbackManager callbackManager;
    private MessageDialog messageDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_share);
        callbackManager = CallbackManager.Factory.create();
        messageDialog = new MessageDialog(this);
        messageDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void shareLink() {
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
        + getResources().getResourcePackageName(R.mipmap.share) + "/"
        + getResources().getResourceTypeName(R.mipmap.share) + "/"
        + getResources().getResourceEntryName(R.mipmap.share));

        Log.d("ScreenActionReceiver", "---uri---" + uri.toString());

        ShareMessengerURLActionButton actionButton =
                new ShareMessengerURLActionButton.Builder()
                        .setTitle("Visit Facebook")
                        .setUrl(Uri.parse("https://www.facebook.com"))
                        .build();
        ShareMessengerGenericTemplateElement genericTemplateElement =
                new ShareMessengerGenericTemplateElement.Builder()
                        .setTitle("Visit Facebook")
                        .setSubtitle("Visit Messenger")
                        .setImageUrl(uri)
                        .setButton(actionButton)
                        .build();
        ShareMessengerGenericTemplateContent genericTemplateContent =
                new ShareMessengerGenericTemplateContent.Builder()
                        .setPageId("com.github.share") // Your page ID, required
                        .setGenericTemplateElement(genericTemplateElement)
                        .build();

        if (messageDialog.canShow(genericTemplateContent)) {
            messageDialog.show(this, genericTemplateContent);
        }
    }
}
