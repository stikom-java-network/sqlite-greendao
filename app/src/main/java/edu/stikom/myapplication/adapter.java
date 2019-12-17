package edu.stikom.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    private List<barang> list_barang;
    private barangadapterfungsi barangadapterfungsi;

    public adapter(List<barang> list_barang, adapter.barangadapterfungsi barangadapterfungsi) {
        this.list_barang = list_barang;
        this.barangadapterfungsi = barangadapterfungsi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_barang,parent,false);
       return new adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    barang b = list_barang.get(position);
    holder.tvnama.setText(b.getNama());

        Locale localid = new Locale("in", "ID")  ;
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localid); //format rupiah
        holder.tvharga.setText(formatRp.format(b.getHarga()));
    }

    @Override
    public int getItemCount() {
        return list_barang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvnama, tvharga;
        ImageView tvhapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnama = itemView.findViewById(R.id.nama);
            tvharga = itemView.findViewById(R.id.harga);
            tvhapus = itemView.findViewById(R.id.hps);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    barangadapterfungsi.onUpdate(getAdapterPosition());
                    return false;
                }
            });

            tvhapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    barangadapterfungsi.onDelete(getAdapterPosition());
                }
            });

        }
    }

    public interface barangadapterfungsi{
        void onUpdate(int position);
        void onDelete (int position);

    }
}
