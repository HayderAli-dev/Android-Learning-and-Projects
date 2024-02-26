package com.example.chatsapp.Adapters;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatsapp.Models.MessageModel;
import com.example.chatsapp.R;
import com.example.chatsapp.databinding.ItemReceiveBinding;
import com.example.chatsapp.databinding.ItemSentBinding;
import com.github.pgreze.reactions.ReactionPopup;
import com.github.pgreze.reactions.ReactionsConfig;
import com.github.pgreze.reactions.ReactionsConfigBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<MessageModel> messages;
    String senderRoom,receiverRoom;
    final int ITEM_SENT=1;
    final int ITEM_RECEIVE=2;


    public MessageAdapter(Context context,ArrayList<MessageModel> messages,String senderRoom,String receiverRoom){
        this.messages=messages;
        this.context=context;
        this.senderRoom=senderRoom;
        this.receiverRoom=receiverRoom;

    }

    @Override
    public int getItemViewType(int position) {
        MessageModel message=messages.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSenderId())){
            return ITEM_SENT;
        }
        else {
            return ITEM_RECEIVE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==ITEM_SENT){
            View view= LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new SendViewHolder(view);
        }
        else {
            View view=LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new ReceiveViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message=messages.get(position);




        int[] reactions=new int[]{
                R.drawable.ic_fb_like,
                R.drawable.ic_fb_love,
                R.drawable.ic_fb_laugh,
                R.drawable.ic_fb_wow,
                R.drawable.ic_fb_sad,
                R.drawable.ic_fb_angry
        };

        ReactionsConfig config = new ReactionsConfigBuilder(context)
                .withReactions(reactions)
                .build();



        ReactionPopup popup = new ReactionPopup(context, config, (pos) -> {
            if (pos<0)
                return false;
            if (holder.getClass()==SendViewHolder.class){
                SendViewHolder viewHolder=(SendViewHolder) holder;
                viewHolder.binding.feeling.setImageResource(reactions[pos]);
                viewHolder.binding.feeling.setVisibility(View.VISIBLE);
            }
            else {
                ReceiveViewHolder viewHolder=(ReceiveViewHolder) holder;
                viewHolder.binding.feeling.setImageResource(reactions[pos]);
                viewHolder.binding.feeling.setVisibility(View.VISIBLE);

            }
message.setFeeling(pos);
               FirebaseDatabase.getInstance().getReference().child("chats").child(senderRoom).child("messages")
                       .child(message.getMessageId()).setValue(message);
               FirebaseDatabase.getInstance().getReference().child("chats").child(receiverRoom).child("messages")
                       .child(message.getMessageId()).setValue(message);

            return true; // true is closing popup, false is requesting a new selection
        });



if (holder.getClass()==SendViewHolder.class){
    SendViewHolder viewHolder=(SendViewHolder) holder;
    //viewHolder.binding.sendingTime.setText(convertTimestampToReadable(message.getTimeStamp()));


    holder.itemView.setOnLongClickListener(v -> {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.inflate(R.menu.message_context_menu); // Create this XML menu
        popupMenu.setOnMenuItemClickListener(item -> {
      if (item.getItemId()==R.id.menu_copy){
          copyToClipboard(message.getMessage());
      } else {
          AlertDialog.Builder builder = new AlertDialog.Builder(context);
          builder.setTitle("Delete Message")
                  .setItems(new CharSequence[]{"Delete for Everyone", "Delete for Me", "Cancel"},
                          (dialog, which) -> {
                              switch (which) {
                                  case 0:
                                      // Handle delete for everyone
                                      deleteMessageForEveryone(message);
                                      break;
                                  case 1:
                                      // Handle delete for me
                                      break;
                                  // case 2: Cancel, no action needed
                              }
                          })
                  .show();
      }
            return true;
        });
        popupMenu.show();
        return true;
    });


    if(message.getMessage().equals("photo")) {
        if (message.isDeleted()){
            viewHolder.binding.image.setVisibility(View.GONE);
            viewHolder.binding.message.setVisibility(View.VISIBLE);
            viewHolder.binding.time.setText(formatDate(message.getTimeStamp()));
            viewHolder.binding.message.setText("You deleted this message");
        }
        else{
            viewHolder.binding.image.setVisibility(View.VISIBLE);
            viewHolder.binding.message.setVisibility(View.GONE);
            viewHolder.binding.time.setText(formatDate(message.getTimeStamp()));
            Glide.with(context)
                    .load(message.getImageUrl())
                    .into(viewHolder.binding.image);
        }
    }
    else {
        if (message.isDeleted()) {
            viewHolder.binding.image.setVisibility(View.GONE);
            viewHolder.binding.message.setVisibility(View.VISIBLE);
            viewHolder.binding.time.setText(formatDate(message.getTimeStamp()));
            viewHolder.binding.message.setText("You deleted this message");
        }
        else {
            viewHolder.binding.image.setVisibility(View.GONE);
            viewHolder.binding.message.setVisibility(View.VISIBLE);
            viewHolder.binding.time.setText(formatDate(message.getTimeStamp()));
            viewHolder.binding.message.setText(message.getMessage());
        }
    }

if (message.getFeeling()>=0){
    viewHolder.binding.feeling.setImageResource(reactions[(int) message.getFeeling()]);
    viewHolder.binding.feeling.setVisibility(View.VISIBLE);
}else {
    viewHolder.binding.feeling.setVisibility(View.GONE);
}

    viewHolder.binding.message.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            popup.onTouch(view,motionEvent);
            return false;
        }
    });

    viewHolder.binding.image.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            popup.onTouch(view,motionEvent);
            return false;
        }
    });

}
else {
    ReceiveViewHolder viewHolder=(ReceiveViewHolder) holder;
    if(message.getMessage().equals("photo")) {
        viewHolder.binding.image.setVisibility(View.VISIBLE);
        viewHolder.binding.message.setVisibility(View.GONE);
        viewHolder.binding.time.setText(formatDate(message.getTimeStamp()));
        Glide.with(context)
                .load(message.getImageUrl())
                .into(viewHolder.binding.image);
    }
    else {
        viewHolder.binding.image.setVisibility(View.GONE);
        viewHolder.binding.message.setVisibility(View.VISIBLE);
        viewHolder.binding.time.setText(formatDate(message.getTimeStamp()));
        viewHolder.binding.message.setText(message.getMessage());
    }


    if (message.getFeeling()>=0){
        viewHolder.binding.feeling.setImageResource(reactions[(int)message.getFeeling()]);
        viewHolder.binding.feeling.setVisibility(View.VISIBLE);
    }else {
        viewHolder.binding.feeling.setVisibility(View.GONE);
    }

    viewHolder.binding.message.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            popup.onTouch(view,motionEvent);
            return false;
        }
    });

    viewHolder.binding.image.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            popup.onTouch(view,motionEvent);
            return false;
        }
    });

}
    }

    @Override
    public int getItemCount() {
       return messages.size();
    }

    public class SendViewHolder extends RecyclerView.ViewHolder{
ItemSentBinding binding;
        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemSentBinding.bind(itemView);

        }
    }
    public class ReceiveViewHolder extends RecyclerView.ViewHolder{
        ItemReceiveBinding binding;
        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemReceiveBinding.bind(itemView);
        }
    }
    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    // When user clicks on "Delete for Everyone"
    private void deleteMessageForEveryone(MessageModel message) {
        message.setDeleted(true);
        message.setMessage("This message was deleted");
        message.setFeeling(-1);
        message.setImageUrl("");

        // Update the status of the message in both sender's and receiver's databases
        FirebaseDatabase.getInstance().getReference().child("chats").child(senderRoom).child("messages")
                .child(message.getMessageId()).setValue(message);

        FirebaseDatabase.getInstance().getReference().child("chats").child(receiverRoom).child("messages")
                .child(message.getMessageId()).setValue(message);
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);

        // Show a toast indicating that the text has been copied to the clipboard
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }



}
