package com.ashfaque.demologinrecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaque.demologinrecyclerview.model.Person;
import com.ashfaque.demologinrecyclerview.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Person> itemList;

    public ItemAdapter(List<Person> itemList) {
        this.itemList = itemList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvAge;
        public TextView tvCity;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvAge = itemView.findViewById(R.id.tvAge);

        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Person item = itemList.get(position);
        holder.tvName.setText("Name : "+item.getName());
        holder.tvCity.setText("City : "+item.getCity());
        holder.tvAge.setText("Age : "+item.getAge());
    }


    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public void updateList( List<Person> itemList)
    {
        this.itemList=itemList;
        notifyDataSetChanged();
    }
}

