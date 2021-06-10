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

import com.example.myapplication.EditActvities.EditBankCard;
import com.example.myapplication.ObjectsRoom.BankCardPass;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BankCardRecyclerAdapter extends RecyclerView.Adapter<BankCardRecyclerAdapter.ViewHolder> {
    private List<BankCardPass> cards;
    private Context context;



    public BankCardRecyclerAdapter(Context context, List<BankCardPass> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public BankCardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bankcard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardRecyclerAdapter.ViewHolder holder, int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy", Locale.US);

        holder.tvDesc.setVisibility(View.GONE);
        holder.tvBankCat.setVisibility(View.GONE);
        holder.tvBankNum.setVisibility(View.GONE);
        holder.tvBank.setVisibility(View.GONE);
        holder.tvPass.setVisibility(View.GONE);
        holder.tvDate.setVisibility(View.GONE);
        holder.tvCVV.setVisibility(View.GONE);

        try {
            String Desc = cards.get(position).getDescription();
            String BankCat = cards.get(position).getCardCat();
            int CardNum = cards.get(position).getCardNum();
            String Bank = cards.get(position).getCardBank();
            int Pass = cards.get(position).getCardPass();
            Date Date = cards.get(position).getUntilDate();
            int CVV = cards.get(position).getCVV();

            if (!Desc.isEmpty()) {
                holder.tvDesc.setText(Desc);
                holder.tvDesc.setVisibility(View.VISIBLE);
            }
            if (!BankCat.isEmpty()) {
                holder.tvBankCat.setText(BankCat);
                holder.tvBankCat.setVisibility(View.VISIBLE);
            }
                holder.tvBankNum.setText(String.valueOf(CardNum));
                holder.tvBankNum.setVisibility(View.VISIBLE);

            if (!Bank.isEmpty()) {
                holder.tvBank.setText(Bank);
                holder.tvBank.setVisibility(View.VISIBLE);
            }
                holder.tvPass.setText(String.valueOf(Pass));
                holder.tvPass.setVisibility(View.VISIBLE);

                holder.tvDate.setText(formatter.format(Date));
                holder.tvDate.setVisibility(View.VISIBLE);

                holder.tvCVV.setText(String.valueOf(CVV));
                holder.tvCVV.setVisibility(View.VISIBLE);

        }catch (NumberFormatException ex){
            Toast.makeText(context, R.string.message_warning, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<BankCardPass> alarms2) {
        this.cards = alarms2;
    }


    void editNote(int position) {
        Intent intent = new Intent(context, EditBankCard.class);
        BankCardPass cards = this.cards.get(position);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("edit_card", cards.getCardNum());
            context.startActivity(intent);
    }

//                AlarmPasswords note = alarms.get(position);
//                alarms.remove(note);
//                alarmDAO.delete(note);



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDesc;
        TextView tvBankCat;
        TextView tvBankNum;
        TextView tvBank;
        TextView tvPass;
        TextView tvDate;
        TextView tvCVV;

        ViewHolder(View itemView) {
            super(itemView);
                tvDesc= (TextView)itemView.findViewById(R.id.tv_bankcard_desc_item);
                tvBankCat= (TextView)itemView.findViewById(R.id.tv_bankcard_item_cat);
                tvBankNum= (TextView)itemView.findViewById(R.id.tv_bankcard_numberCard_item);
                tvBank= (TextView)itemView.findViewById(R.id.tv_bankcard_bank_item);
                tvPass= (TextView)itemView.findViewById(R.id.tv_cardbank_item_password);
                tvDate= (TextView)itemView.findViewById(R.id.tv_bankcard_item_date);
                tvCVV= (TextView)itemView.findViewById(R.id.tv_bankcard_item_cvv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editNote(getAdapterPosition());
                }
            });
        }

    }
}
