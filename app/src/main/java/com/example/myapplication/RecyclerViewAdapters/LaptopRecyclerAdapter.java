package com.example.myapplication.RecyclerViewAdapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DB.App;
import com.example.myapplication.DB.AppDatabase;
import com.example.myapplication.EditActvities.EditLaptop;
import com.example.myapplication.ObjectsRoom.AlarmPasswords;
import com.example.myapplication.ObjectsRoom.LaptopDAO;
import com.example.myapplication.ObjectsRoom.LaptopPasswords;
import com.example.myapplication.R;

import java.util.List;

public class LaptopRecyclerAdapter extends RecyclerView.Adapter<LaptopRecyclerAdapter.ViewHolder> {
    private List<LaptopPasswords> laptops;
    private Context context;



    public LaptopRecyclerAdapter(Context context, List<LaptopPasswords> laptops) {
        this.context = context;
        this.laptops = laptops;
    }

    @NonNull
    @Override
    public LaptopRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laptop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopRecyclerAdapter.ViewHolder holder, int position) {

        holder.serial.setVisibility(View.GONE);
        holder.owner.setVisibility(View.GONE);
        holder.model.setVisibility(View.GONE);
        holder.nameCatLap.setVisibility(View.GONE);
        holder.laptopPass.setVisibility(View.GONE);

        try {
            String serialLap = laptops.get(position).getSerialNumber();
            String ownerLap = laptops.get(position).getLaptopOwnerName();
            String modelLap = laptops.get(position).getLaptopModel();
            String catNameLap = laptops.get(position).getNameCat();
            String laptopPasLap = laptops.get(position).getLaptopPass();

            if (!serialLap.isEmpty()) {
                holder.serial.setText(serialLap);
                holder.serial.setVisibility(View.VISIBLE);
            }
            if (!ownerLap.isEmpty()) {
                holder.owner.setText(ownerLap);
                holder.owner.setVisibility(View.VISIBLE);
            }
            if (!modelLap.isEmpty()) {
                holder.model.setText(modelLap);
                holder.model.setVisibility(View.VISIBLE);
            }
            if (!catNameLap.isEmpty()) {
                holder.nameCatLap.setText(catNameLap);
                holder.nameCatLap.setVisibility(View.VISIBLE);
            }
            if (!laptopPasLap.isEmpty()) {
                holder.laptopPass.setText(laptopPasLap);
                holder.laptopPass.setVisibility(View.VISIBLE);
            }
        }catch (NumberFormatException ex){
            Toast.makeText(context, R.string.message_warning, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    public void setAlarms(List<AlarmPasswords> alarms2) {
     //   this.alarms = alarms2;
    }


    void editNote(int position) {
        Intent intent = new Intent(context, EditLaptop.class);
        LaptopPasswords laptop = laptops.get(position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("edit_laptop", laptop.getSerialNumber());
        context.startActivity(intent);
    }

    void deleteNote(final int position) {
        final AppDatabase db = App.getInstance().getDatabase();
        LaptopDAO laptopDAO = db.LaptopDAO();
        List<LaptopPasswords> laptops = laptopDAO.getAll();



        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setMessage(R.string.message_dialog_delete);
        dialogBuilder.setTitle(R.string.title_dialog_delete);
        dialogBuilder.setIcon(R.drawable.ic_delete_forever_black_24dp);

        dialogBuilder.setPositiveButton(R.string.text_button_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                LaptopPasswords laptop = laptops.get(position);
                laptops.remove(laptop);
                laptopDAO.delete(laptop);
                 LaptopRecyclerAdapter.this.notifyDataSetChanged();
            }
        });
        dialogBuilder.setNegativeButton(R.string.text_button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView serial;
        TextView owner;
        TextView model;
        TextView nameCatLap;
        TextView laptopPass;

        ViewHolder(View itemView) {
            super(itemView);
            serial= itemView.findViewById(R.id.tv_serial_laptop);
            owner = itemView.findViewById(R.id.laptopOwner);
            model = itemView.findViewById(R.id.laptopModel);
            nameCatLap = itemView.findViewById(R.id.tv_categoryLaptop);
            laptopPass = itemView.findViewById(R.id.tv_laptopPass);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editNote(getAdapterPosition());
                }
            });

        }

    }
}
