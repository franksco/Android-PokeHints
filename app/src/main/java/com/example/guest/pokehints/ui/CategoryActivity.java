package com.example.guest.pokehints.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.pokehints.Constants;
import com.example.guest.pokehints.R;
import com.example.guest.pokehints.adapters.FirebasePostViewHolder;
import com.example.guest.pokehints.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {
    private DatabaseReference mPostReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private List<String> categories = new ArrayList<>();

    @Bind(R.id.ListView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        mPostReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORIES);
        Query queryRef = mPostReference.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                categories.add(snapshot.getKey());
                setListView();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                categories.remove(dataSnapshot.getKey());
                setListView();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                categories.remove(dataSnapshot.getKey());
                setListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });



    }

    private void setListView() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String category = ((TextView)view).getText().toString();
                Intent intent = new Intent(CategoryActivity.this, PostListActivity.class);
                intent.putExtra("category", category);
            }
        });

    }

}
