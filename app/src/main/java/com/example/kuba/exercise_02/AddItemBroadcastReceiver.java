package com.example.kuba.exercise_02;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AddItemBroadcastReceiver extends BroadcastReceiver {

    private int notificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        createNotification(context, id, name, username);
    }

    private void createNotification(Context context, int id, String name, String username) {

        Intent intentList = new Intent("com.example.kuba.app.activity.ITEM_LIST_ACTIVITY");

        Intent intentEdit = new Intent("com.example.kuba.app.activity.EDIT_ITEM_ACTIVITY");
        intentEdit.putExtra("id", id);

        PendingIntent pendingIntentList =
                PendingIntent.getActivity(context, 0, intentList, 0);

        PendingIntent pendingIntentEdit =
                PendingIntent.getActivity(context, 0, intentEdit, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setContentTitle("ITEM ADDED!")
                        .setSmallIcon(R.drawable.ic_add_black_24dp)
                        .addAction(R.drawable.ic_list_black_24dp, "Go to shopping list", pendingIntentList)
                        .addAction(R.drawable.ic_edit_black_24dp, "Edit item  ", pendingIntentEdit)
                        .setAutoCancel(true);

        if (username != null && !username.isEmpty()) {
            builder.setContentText(name + " added to " + username + "'s shopping list!");
        } else {
            builder.setContentText(name + " added to shopping list!");
        }

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notificationId++, builder.build());
    }
}
