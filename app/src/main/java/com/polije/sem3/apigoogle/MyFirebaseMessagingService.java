package com.polije.sem3.apigoogle;
import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
    }

    @Override
    public void onDeletedMessages() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getData().size() > 0 && message.getNotification() != null){
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Notif Type : " + message.getNotification().getTitle());
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Notif Body : " + message.getNotification().getBody());
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Notif Time : " + message.getSentTime());
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Notif From : " + message.getFrom());
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Notif Click : " + message.getFrom());
//
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Key 1 : " + message.getData().get("key_1"));
//            LogApp.info(this, LogTag.ON_NOTIFICATION, "Key 2 : " + message.getData().get("key_2"));
//
//
//            var clickAction = message.getNotification().getClickAction();

//            if (clickAction != null){
//                startActivity(
//                        new Intent(Intent.ACTION_VIEW, Uri.parse(clickAction))
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                );
//            }

        }

    }


}
