package com.example.whatsappcloneasad.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappcloneasad.Model.Message
import com.example.whatsappcloneasad.R
import com.example.whatsappcloneasad.databinding.ItemReceiveBinding
import com.example.whatsappcloneasad.databinding.ItemSendBinding
import com.github.pgreze.reactions.ReactionPopup
import com.github.pgreze.reactions.ReactionPopupStateChangeListener
import com.github.pgreze.reactions.ReactionsConfig
import com.github.pgreze.reactions.ReactionsConfigBuilder
import com.github.pgreze.reactions.dsl.reactionConfig
import com.github.pgreze.reactions.dsl.reactions
import com.google.common.collect.Iterables.indexOf
import com.google.firebase.auth.FirebaseAuth

class Message_Adapter(var context:Context,var messages:ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding:ItemSendBinding=ItemSendBinding.bind(itemView)
    }
    inner class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: ItemReceiveBinding =ItemReceiveBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType== VIEW_TYPE_SEND){
            val view:View=LayoutInflater.from(context).inflate(R.layout.item_send,parent,false)
            return SendViewHolder(view)
        } else{
            val view:View=LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false)
            return ReceiveViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
   return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var message:Message=messages.get(position)
        val reactions = intArrayOf(
            R.drawable.ic_fb_like,
            R.drawable.ic_fb_love,
            R.drawable.ic_fb_laugh,
            R.drawable.ic_fb_wow,
            R.drawable.ic_fb_sad,
            R.drawable.ic_fb_angry
        )

        val config = ReactionsConfigBuilder(context)
            .withReactions(reactions)
            .build()

        val popup = ReactionPopup(context, config) { position ->
            if (holder is SendViewHolder) {
               // holder.binding.feelings.setImageResource(reactions[position])
            }
            true // true is closing popup, false is requesting a new selection
        }


        if (holder is SendViewHolder){
            holder.binding.message.setText(message.message)
            holder.binding.message.setOnTouchListener(object:OnTouchListener{
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event != null && v != null) {

                        popup.onTouch(v,event)
                    }
                    return false
                }
            })
        } else if (holder is ReceiveViewHolder){
            holder.binding.message.setText(message.message)
            holder.binding.message.setOnTouchListener(object:OnTouchListener{
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event != null && v != null) {

                        popup.onTouch(v,event)
                    }
                    return false
                }
            })
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message:Message=messages.get(position)
        if (FirebaseAuth.getInstance().uid.equals(message.senderId)){
            return VIEW_TYPE_SEND
        } else{
            return VIEW_TYPE_RECEIVE
        }
        return super.getItemViewType(position)
    }

    companion object {
        private const val VIEW_TYPE_SEND = 1
        private const val VIEW_TYPE_RECEIVE = 2
    }
}