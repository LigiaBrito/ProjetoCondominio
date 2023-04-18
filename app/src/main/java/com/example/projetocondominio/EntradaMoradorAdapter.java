package com.example.projetocondominio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class EntradaMoradorAdapter extends RecyclerView.Adapter<EntradaMoradorAdapter.EntradaMoradorViewHolder> {

    private Context mContext;
    private List<EntradaMorador> mEntradaMoradorList;

    public EntradaMoradorAdapter(Context context, List<EntradaMorador> entradaMoradorList) {
        mContext = context;
        mEntradaMoradorList = entradaMoradorList;
    }

    @NonNull
    @Override
    public EntradaMoradorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_mostrar_entrada_saida, parent, false);
        return new EntradaMoradorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntradaMoradorViewHolder holder, int position) {
        EntradaMorador entradaMorador = mEntradaMoradorList.get(position);
        holder.dataTextView.setText(entradaMorador.getHoraEntrada());
        holder.moradorTextView.setText(entradaMorador.getNome());
    }

    @Override
    public int getItemCount() {
        return mEntradaMoradorList.size();
    }

    public static class EntradaMoradorViewHolder extends RecyclerView.ViewHolder {

        TextView dataTextView;
        TextView moradorTextView;

        public EntradaMoradorViewHolder(@NonNull View itemView) {
            super(itemView);
            dataTextView = itemView.findViewById(R.id.tv_data_entrada);
            moradorTextView = itemView.findViewById(R.id.tv_nome_morador);
        }
    }
}
