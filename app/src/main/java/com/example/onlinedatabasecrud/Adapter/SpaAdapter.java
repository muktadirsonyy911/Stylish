package com.example.onlinedatabasecrud.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;
import com.example.onlinedatabasecrud.R;

import java.util.ArrayList;
import java.util.List;

public class SpaAdapter extends RecyclerView.Adapter<SpaAdapter.ViewHolder>
        implements Filterable {
    private List<ShopsInfo> list;
    private List<ShopsInfo> listFull;
    private SpaAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(SpaAdapter.OnItemClickListener listener) {
        mListener=listener;
    }


    public SpaAdapter(List<ShopsInfo> list) {
        this.list = list;
        listFull = new ArrayList<>(list);
    }


    @NonNull
    @Override
    public SpaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spa_info_list, parent, false);
        SpaAdapter.ViewHolder holder = new SpaAdapter.ViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpaAdapter.ViewHolder holder, final int position) {

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


        public ViewHolder(@NonNull View itemView, SpaAdapter.OnItemClickListener mListener) {
            super(itemView);

            name = itemView.findViewById(R.id.NameTV);
            address = itemView.findViewById(R.id.AddressTV);
            phone = itemView.findViewById(R.id.PhoneTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SpaAdapter.this.mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            SpaAdapter.this.mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}


