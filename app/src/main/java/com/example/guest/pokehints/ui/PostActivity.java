package com.example.guest.pokehints.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.pokehints.Constants;
import com.example.guest.pokehints.R;
import com.example.guest.pokehints.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.username) EditText mUsername;
    @Bind(R.id.content) EditText mContent;
    @Bind(R.id.category) EditText mCategories;
    @Bind(R.id.submit) Button mSubmitButton;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitButton){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy h:mm a");
            String formattedDate = sdf.format(date);
            String username = mUsername.getText().toString();
            String content = mContent.getText().toString();
            String categories = mCategories.getText().toString();
            List<String> listOfCategories = Arrays.asList(categories.split("\\s*,\\s*"));

            Post post = new Post(username, content, listOfCategories, formattedDate);

            String key = mDatabase.child("posts").push().getKey();

            Map<String, Object> postValues = post.toMap();

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/posts/" + key, postValues);
            for(String category : listOfCategories){
                childUpdates.put("/categories/" + category + "/" + key, postValues);
            }


            mDatabase.updateChildren(childUpdates);

            Toast.makeText(PostActivity.this, "Posted", Toast.LENGTH_SHORT).show();
//            DatabaseReference postRef = FirebaseDatabase
//                    .getInstance()
//                    .getReference(Constants.FIREBASE_CHILD_POSTS);
//            String key = postRef.push().getKey();
//            postRef.push().setValue(post);
//            Toast.makeText(PostActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
