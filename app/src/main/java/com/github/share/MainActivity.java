package com.github.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFacebook = findViewById(R.id.facebook);

        btnFacebook.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnFacebook) {
            startActivity(new Intent(this, FacebookShareActivity.class));
        }
    }


}
