package com.example.claudius.saveme;

/**
 * Created by Claudius on 18.12.16.
 */

public interface ActivityCommunicator {
    public void passusername(String someValue, int type);

    public void sendDates(int Day, int month, int year, int Type);
}
