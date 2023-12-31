package com.example.kiemtra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kiemtra.DAO.ProductDAO;
import com.example.kiemtra.R;
import com.example.kiemtra.models.SanPham;

import java.util.List;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<SanPham> listProduct;
    private ProductDAO productDAO;
    private OnDeleteClickListener deleteClickListener;
    private Context context;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public ProductAdapter(List<SanPham> listProduct, OnDeleteClickListener listener, Context context) {
        this.listProduct = listProduct;
        this.deleteClickListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewProduct = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanpham, parent, false);
        return new ViewHolder(viewProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham p = listProduct.get(position);
        holder.txtName.setText(p.getName());
        holder.txtPrice.setText(String.valueOf(p.getPrice()));
        holder.deleteButton.setId(position);

        if (p.getImage().startsWith("https://")) {
            Glide.with(holder.imgView.getContext()).load(p.getImage()).into(holder.imgView);
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtName, txtPrice;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            deleteButton = itemView.findViewById(R.id.xoa);
        }
    }

    public List<SanPham> getListProduct() {
        return listProduct;
    }

    public Context getContext() {
        return context;
    }

    public void refreshList(List<SanPham> newList) {
        listProduct.clear();
        listProduct.addAll(newList);
        notifyDataSetChanged();
    }

    // Function to delete item from the list
    public int deleteItem(int position) {
        int id = listProduct.get(position).getId();
        listProduct.remove(position);
        notifyItemRemoved(position);
        return id;
    }
}
