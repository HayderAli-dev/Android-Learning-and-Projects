package com.example.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class smsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       Bundle bundle=intent.getExtras();
       Object[] smsObj=(Object[]) bundle.get("pdus");
        for (Object obj:smsObj) {
            SmsMessage message=SmsMessage.createFromPdu(((byte[])obj));
            Log.d("Messages","Message No: "+message.getDisplayOriginatingAddress()
                    +" Message Body: "+message.getDisplayMessageBody());
        }

    }
}
