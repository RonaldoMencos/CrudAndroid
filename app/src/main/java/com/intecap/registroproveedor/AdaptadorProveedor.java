package com.intecap.registroproveedor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorProveedor extends RecyclerView.Adapter<AdaptadorProveedor.ClientesViewHolder> {

    ArrayList<Proveedor> listadoProveedor;

    public AdaptadorProveedor(ArrayList<Proveedor> listadoProveedor) {
        this.listadoProveedor = listadoProveedor;
    }

    @NonNull
    @Override
    public AdaptadorProveedor.ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado_proveedores,null,false);
        return new ClientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProveedor.ClientesViewHolder holder, int position) {
        holder.vistaNombreCompania.setText(listadoProveedor.get(position).getNombreCompania());
        holder.vistaNombreContacto.setText(listadoProveedor.get(position).getCargoContacto());
        holder.vistaDireccion.setText(listadoProveedor.get(position).getDireccion());
    }

    @Override
    public int getItemCount() {
        return listadoProveedor.size();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView vistaNombreCompania,vistaNombreContacto,vistaDireccion;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            vistaNombreCompania=itemView.findViewById(R.id.vistaNombreCompania);
            vistaNombreContacto=itemView.findViewById(R.id.vistaNombreContacto);
            vistaDireccion=itemView.findViewById(R.id.vistaDireccion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context pantVisualizar = v.getContext();
                    Intent i = new Intent(pantVisualizar, VerProveedor.class);
                    i.putExtra("id", listadoProveedor.get(getAdapterPosition()).getId());
                    pantVisualizar.startActivity(i);
                }
            });
        }
    }
}
