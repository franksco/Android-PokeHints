package com.example.guest.pokehints.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.pokehints.Constants;
import com.example.guest.pokehints.R;
import com.example.guest.pokehints.models.Post;
import com.example.guest.pokehints.ui.PostDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 7/12/16.
 */
public class FirebasePostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebasePostViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRestaurant(Post post) {
        TextView TextView = (TextView) mView.findViewById(R.id.textView);
//        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
//        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
//
//        nameTextView.setText(post.getUsername());
//        categoryTextView.setText(post.getCategories().get(0));
//        ratingTextView.setText("Rating: " + post.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Post> posts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POSTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    posts.add(snapshot.getValue(Post.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PostDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(posts));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
