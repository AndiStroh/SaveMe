package com.example.claudius.saveme.Interfaces;

import android.graphics.Bitmap;

/**
 * Created by Claudius on 18.12.16.
 */

//Interface das es ermöglicht nachrichten vom Fragment zu Activity zu senden.


public interface ActivityCommunicator {
     void passStrings(String someValue, int type);

     void sendDates(int Day, int month, int year, int Type);

     void sendBitmap(Bitmap bitmap);
}
