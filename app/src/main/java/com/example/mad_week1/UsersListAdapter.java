package com.example.mad_week1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.DataUser;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersViewHolder> {
    private ArrayList<DataUser> listUser;

    public UsersListAdapter(ArrayList<DataUser> listUser){this.listUser = listUser;}
    @NonNull
    @Override
    public UsersListAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_user, parent, false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.UsersViewHolder holder, int position) {
        holder.textView_cardname.setText(listUser.get(position).getName());
        holder.textView_cardage.setText(String.valueOf(listUser.get(position).getAge()));
        holder.textView_cardaddress.setText(listUser.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Details_Activity.class);
                intent.putParcelableArrayListExtra("arraylist", listUser);
                intent.putExtra("position",position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_cardname, textView_cardage, textView_cardaddress;
        private ImageView imageView_profpic;
        private CardView cardView;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_cardname = itemView.findViewById(R.id.textView_cardname);
            textView_cardage = itemView.findViewById(R.id.textView_cardage);
            textView_cardaddress = itemView.findViewById(R.id.textView_cardaddress);
            imageView_profpic = itemView.findViewById(R.id.imageView_profpic);

        }
    }
}
