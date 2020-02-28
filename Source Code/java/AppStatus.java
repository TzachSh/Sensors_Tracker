package com.Course.DesignPatterns;


import android.app.Activity;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppStatus {
    private TextView txtLastUpdate;
    private TextView txtStatus;
    private DateFormat dateFormat;

    public AppStatus(Activity activity) {
        txtLastUpdate = activity.findViewById(R.id.txtLastUpdate);
        txtStatus = activity.findViewById(R.id.txtStatus);
        dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
    }

    public void setCurrentTime() {
        txtLastUpdate.setText(dateFormat.format(new Date()));
    }

    public void setStatus(String status) {
        txtStatus.setText(status);
    }
}
