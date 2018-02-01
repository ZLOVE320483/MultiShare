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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFacebook = findViewById(R.id.facebook);
        btnMessenger = findViewById(R.id.messenger);

        btnFacebook.setOnClickListener(this);
        btnMessenger.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnFacebook) {
            startActivity(new Intent(this, FacebookShareActivity.class));
        } else if (view == btnMessenger) {
            Toast.makeText(MainActivity.this, "由于国内无法安装messenger，并未测试通过...", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, MessengerShareActivity.class));
        }
    }


}
