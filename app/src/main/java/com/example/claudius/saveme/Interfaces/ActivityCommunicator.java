package com.example.claudius.saveme.Interfaces;

import android.graphics.Bitmap;



//Interface das es ermöglicht nachrichten vom Fragment zu Activity zu senden.


public interface ActivityCommunicator {
     void passStrings(String someValue, int type);

     void sendDates(int Day, int month, int year, int Type);

     void sendBitmap(Bitmap bitmap);
}
