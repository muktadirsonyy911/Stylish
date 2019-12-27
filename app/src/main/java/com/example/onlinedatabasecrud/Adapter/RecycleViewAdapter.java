package com.example.onlinedatabasecrud.Adapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.onlinedatabasecrud.R;
import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;
import com.example.onlinedatabasecrud.RecycleView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

    public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
            implements Filterable {
        private List<ShopsInfo> list;
        private List<ShopsInfo> listFull;
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener=listener;
        }


        public RecycleViewAdapter(List<ShopsInfo> list) {
            this.list = list;
            listFull = new ArrayList<>(list);
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_info_list, parent, false);
            ViewHolder holder = new ViewHolder(view, mListener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            //for adding any other items
            holder.name.setText(list.get(position).getShopName());
            holder.address.setText(list.get(position).getShopAddress());
            holder.phone.setText(list.get(position).getShopPhone());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public Filter getFilter() {
            return filter;
        }

        private Filter filter = new Filter() {
            @Override
            //run on background thread
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ShopsInfo> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(listFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (ShopsInfo info : listFull) {
                        if (info.getShopAddress().toLowerCase().contains(filterPattern)) {
                            filteredList.add(info);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;

                return filterResults;
            }

            @Override
            //run on a ui thread
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list.clear();
                list.addAll((List) results.values);
                notifyDataSetChanged();

            }
        };


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, address, phone;


            public ViewHolder(@NonNull View itemView, OnItemClickListener mListener) {
                super(itemView);

                name = itemView.findViewById(R.id.NameTV);
                address = itemView.findViewById(R.id.AddressTV);
                phone = itemView.findViewById(R.id.PhoneTV);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (RecycleViewAdapter.this.mListener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                RecycleViewAdapter.this.mListener.onItemClick(position);
                            }
                        }
                    }
                });


            }
        }
    }

