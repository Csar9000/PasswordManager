package com.example.myapplication.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.EditActvities.EditAlarm;
import com.example.myapplication.ObjectsRoom.AlarmPasswords;
import com.example.myapplication.R;


import java.util.List;

public class AlarmRecyclerAdapter extends RecyclerView.Adapter<AlarmRecyclerAdapter.ViewHolder> {
    private List<AlarmPasswords> alarms;
    private Context context;



    public AlarmRecyclerAdapter(Context context, List<AlarmPasswords> alarms) {
        this.context = context;
        this.alarms = alarms;
    }

    @NonNull
    @Override
    public AlarmRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmRecyclerAdapter.ViewHolder holder, int position) {



        holder.tvOfficeId.setVisibility(View.GONE);
        holder.tvNameCat.setVisibility(View.GONE);
        holder.tvPassword.setVisibility(View.GONE);

        try {
            String noteTitle = alarms.get(position).getOfficeID();
            String noteContent = alarms.get(position).getNameCat();
            int password = alarms.get(position).getPassword();

            if (!noteTitle.isEmpty()) {
                holder.tvOfficeId.setText(noteTitle);
                holder.tvOfficeId.setVisibility(View.VISIBLE);
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
        return alarms.size();
    }

    public void setAlarms(List<AlarmPasswords> alarms2) {
        this.alarms = alarms2;
    }


    void editAlarm(int position) {
        Intent intent = new Intent(context, EditAlarm.class);
        AlarmPasswords alarm = alarms.get(position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("edit_alarm", alarm.getOfficeID());

            context.startActivity(intent);



    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOfficeId;
        TextView tvNameCat;
        TextView tvPassword;
        TextView tvDescription;

        ViewHolder(View itemView) {
            super(itemView);
            tvOfficeId = itemView.findViewById(R.id.tv_officeID);
            tvNameCat = itemView.findViewById(R.id.tv_catNameO);
            tvPassword = itemView.findViewById(R.id.id_passO);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editAlarm(getAdapterPosition());
                }
            });
        }

    }
}
