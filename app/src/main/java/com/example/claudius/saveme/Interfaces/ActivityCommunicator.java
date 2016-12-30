package com.example.claudius.saveme.Interfaces;

import android.graphics.Bitmap;

/**
 * Created by Claudius on 18.12.16.
 */

//Interface das es ermöglicht nachrichten vom Fragment zu Activity zu senden.

    //Mann muss die funktionen in der empfangsactivity einfach nur überschreiben um etwas empfangen zu können.

    /*

    Mann muss isch eine Variable     private ActivityCommunicator activityCommunicator; im Fragment erstellen

    An das der onAttach im Fragment müssen diese Zeilen kommen

    context = getActivity();
        activityCommunicator =(ActivityCommunicator)context;

        wenn man jetzt etwas senden möchte kann man zum Beispiel folgendes machen

         activityCommunicator.sendDates(birthdayDay,birthdayMonth,birthdayYear,cA.getTypeBirthday());

         Empfangen kann man es in der Activity indem man die jeweilige Methode überschreibt */


public interface ActivityCommunicator {
    public void passStrings(String someValue, int type);

    public void sendDates(int Day, int month, int year, int Type);

    public void sendBitmap(Bitmap bitmap);
}
