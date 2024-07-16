package com.example.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chatapplication.ChatActivity;
import com.example.chatapplication.R;
import com.example.chatapplication.Utils.AndroidUtil;
import com.example.chatapplication.Utils.FirebaseUtil;
import com.example.chatapplication.model.userModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchUserAdapter extends FirestoreRecyclerAdapter<userModel,SearchUserAdapter.userModelViewHolder> {

    Context context;
    public SearchUserAdapter(@NonNull FirestoreRecyclerOptions<userModel> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull userModelViewHolder userModelViewHolder, int i, @NonNull userModel userModel) {
        userModelViewHolder.usernameText.setText(userModel.getUsername());
        userModelViewHolder.phoneText.setText(userModel.getPhone());
        if(userModel.getUserId().equals(FirebaseUtil.currentUserId())){
            userModelViewHolder.usernameText.setText(userModel.getUsername()+"(Me)");
        }

        userModelViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            AndroidUtil.passUserModelAsIntent(intent, userModel);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @NonNull
    @Override
    public userModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_user_recycler,parent,false);
        return new userModelViewHolder(view);
    }

    class userModelViewHolder extends RecyclerView.ViewHolder{

        TextView usernameText;
        TextView phoneText;
        ImageView profilePic;
        public userModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            phoneText = itemView.findViewById(R.id.phone_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
