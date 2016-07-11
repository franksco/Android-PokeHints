package com.example.guest.pokehints.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.pokehints.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.header) TextView mHeader;
    @Bind(R.id.catButton) Button mCatButton;
    @Bind(R.id.postButton) Button mPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface pokemonk = Typeface.createFromAsset(getAssets(), "fonts/pokemonk.otf");
        mHeader.setTypeface(pokemonk);

        mCatButton.setOnClickListener(this);
        mPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mCatButton) {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        }
        if(view == mPostButton) {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        }
    }
}
