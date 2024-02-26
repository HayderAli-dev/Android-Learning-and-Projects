package com.example.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {
    Context context;
    ArrayList<contact_Model> arrContact=new ArrayList<>();
    public RecyclerContactAdapter(Context context,ArrayList<contact_Model> arrContact){
        this.context=context;
        this.arrContact=arrContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v=  LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
      ViewHolder viewHolder=new ViewHolder(v);
      return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.txtName.setText(arrContact.get(position).name);
holder.txtNumber.setText(arrContact.get(position).number);
holder.img.setImageResource(arrContact.get(position).img);
setAnimation(holder.itemView,position);

holder.llcard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.add_update_layout);
        dialog.show();

        EditText edtName=dialog.findViewById(R.id.edtContactName);
        EditText edtNumber=dialog.findViewById(R.id.edtContactNumber);
        Button btnAction=dialog.findViewById(R.id.btnAction);
        TextView txtView=dialog.findViewById(R.id.txtView);

        btnAction.setText("Update");
        txtView.setText("Update Contact");

        edtName.setText(arrContact.get(position).name);
        edtNumber.setText(arrContact.get(position).number);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name="";
                String number="";
                if(!edtName.getText().toString().equals("")){
                    name=edtName.getText().toString();
                }
                else {
                    Toast.makeText(context,"Please Enter Contact Name",Toast.LENGTH_LONG).show();
                }
                if(!edtNumber.getText().toString().equals("")){
                    number=edtNumber.getText().toString();
                }
                else {
                    Toast.makeText(context,"Please Enter Contact Number",Toast.LENGTH_LONG).show();
                }
                arrContact.set(position,new contact_Model(R.drawable.conc,name,number));
                notifyItemChanged(position);
               dialog.dismiss();
            }

        });
        dialog.show();
 }
});

holder.llcard.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context).setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete")
                .setIcon(R.drawable.baseline_delete_24).
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrContact.remove(position);
                        notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
        return true;
    }
});




    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtNumber;
        ImageView img;

        LinearLayout llcard;

         public ViewHolder( View item) {
             super(item);
             txtName=item.findViewById(R.id.contactName);
             txtNumber=item.findViewById(R.id.contactNumber);
             img=item.findViewById(R.id.contactImage);
             llcard=item.findViewById(R.id.llrow);
         }
    }
    private void setAnimation(View viewToAnimate,int position){
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.contact_animation);
        viewToAnimate.startAnimation(animation);


    }

}
