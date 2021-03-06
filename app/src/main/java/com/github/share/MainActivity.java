package com.github.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFacebook;
    private Button btnMessenger;
    private Button btnInstagram;
    private Button btnWhatsApp;
    private Button btnTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFacebook = findViewById(R.id.facebook);
        btnMessenger = findViewById(R.id.messenger);
        btnInstagram = findViewById(R.id.instagram);
        btnWhatsApp = findViewById(R.id.whatsapp);
        btnTwitter = findViewById(R.id.twitter);

        btnFacebook.setOnClickListener(this);
        btnMessenger.setOnClickListener(this);
        btnInstagram.setOnClickListener(this);
        btnWhatsApp.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnFacebook) {
            startActivity(new Intent(this, FacebookShareActivity.class));
        } else if (view == btnMessenger) {
            Toast.makeText(MainActivity.this, "由于国内无法安装messenger，并未测试通过...", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, MessengerShareActivity.class));
        } else if (view == btnInstagram) {
            startActivity(new Intent(this, InstagramShareActivity.class));
        } else if (view == btnWhatsApp) {
            startActivity(new Intent(this, WhatsAppShareActivity.class));
        } else if (view == btnTwitter) {
            startActivity(new Intent(this, TwitterShareActivity.class));
        }
    }


}
