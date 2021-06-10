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
import com.example.myapplication.EditActvities.EditBicycle;
import com.example.myapplication.ObjectsRoom.BicycleDAO;
import com.example.myapplication.ObjectsRoom.BicyclePasswords;
import com.example.myapplication.R;

import java.util.List;

public class BicycleRecyclerAdapter extends RecyclerView.Adapter<BicycleRecyclerAdapter.ViewHolder> {
    private List<BicyclePasswords> bicyclies;
    private Context context;



    public BicycleRecyclerAdapter(Context context, List<BicyclePasswords> bicyclies) {
        this.context = context;
        this.bicyclies = bicyclies;
    }

    @NonNull
    @Override
    public BicycleRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bicycle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BicycleRecyclerAdapter.ViewHolder holder, int position) {



        holder.tvOwnerName.setVisibility(View.GONE);
        holder.tvNameCat.setVisibility(View.GONE);
        holder.tvPassword.setVisibility(View.GONE);

        try {
            String noteTitle = bicyclies.get(position).getOwnerName();
            String noteContent = bicyclies.get(position).getNameCat();
            int password = bicyclies.get(position).getPassword();

            if (!noteTitle.isEmpty()) {
                holder.tvOwnerName.setText(noteTitle);
                holder.tvOwnerName.setVisibility(View.VISIBLE);
            }
            if (!noteContent.isEmpty()) {
                holder.tvNameCat.setText(noteContent);
                holder.tvNameCat.setVisibility(View.VISIBLE);
            }
                holder.tvPassword.setText(String.valueOf(password));
                holder.tvPassword.setVisibility(View.VISIBLE);

        }catch (NumberFormatException ex){
            Toast.makeText(context, R.string.message_warning, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return bicyclies.size();
    }

    public void setBicyclies(List<BicyclePasswords> bicyclies) {
        this.bicyclies = bicyclies;
    }


    void editBic(int position) {
        Intent intent = new Intent(context, EditBicycle.class);
        BicyclePasswords bicyclePasswords = bicyclies.get(position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("edit_bicycle", bicyclePasswords.getOwnerName());
            context.startActivity(intent);
    }

    void deleteNote(final int position) {
        final AppDatabase db = App.getInstance().getDatabase();
        BicycleDAO bicycleDAO = db.BicycleDAO();
        List<BicyclePasswords> bicyclePasswords1 = bicycleDAO.getAll();



        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        dialogBuilder.setMessage(R.string.message_dialog_delete);
        dialogBuilder.setTitle(R.string.title_dialog_delete);
        dialogBuilder.setIcon(R.drawable.ic_delete_forever_black_24dp);

        dialogBuilder.setPositiveButton(R.string.text_button_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                BicyclePasswords bicycle = bicyclePasswords1.get(position);
                bicyclies.remove(bicycle);
                bicycleDAO.delete(bicycle);
                 BicycleRecyclerAdapter.this.notifyDataSetChanged();
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
        TextView tvOwnerName;
        TextView tvNameCat;
        TextView tvPassword;

        ViewHolder(View itemView) {
            super(itemView);
            tvOwnerName = itemView.findViewById(R.id.tv_ownerBicycle);
            tvNameCat = itemView.findViewById(R.id.tv_categoryBycicle);
            tvPassword = itemView.findViewById(R.id.tv_passwordBicycle);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editBic(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    deleteNote(getAdapterPosition());
                    return true;
                }
            });
        }

    }
}
