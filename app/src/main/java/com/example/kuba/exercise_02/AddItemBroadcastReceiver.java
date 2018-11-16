package com.example.kuba.exercise_02;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AddItemBroadcastReceiver extends BroadcastReceiver {

    private static final String ADD_ITEM_CHANNEL = "add_item_channel";
    private int notificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String owner = intent.getStringExtra("owner");
        createNotification(context, id, name, owner);
    }

    private void createNotification(Context context, int id, String name, String owner) {

        Intent intentList = new Intent("com.hmkcode.android.another.app.ANOTHER_ACTIVITY");
        Intent intentEdit = new Intent("com.hmkcode.android.another.app.AND_ANOTHER_ACTIVITY");
        intentEdit.putExtra("id", id);

        PendingIntent pendingIntentList =
                PendingIntent.getActivity(context, 0, intentList, 0);
        PendingIntent pendingIntentEdit =
                PendingIntent.getActivity(context, 0, intentEdit, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("ITEM ADDED!")
                        .addAction(R.drawable.ic_launcher_background, "LOL", pendingIntentList)
                        .addAction(R.drawable.ic_launcher_background, "LOL2", pendingIntentEdit);
        if (owner != null && !owner.isEmpty()) {
            builder.setContentText(name + " added to " + owner + "'s shopping list!");
        } else {
            builder.setContentText(name + " added to shopping list!");
        }

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notificationId++, builder.build());
    }
}
