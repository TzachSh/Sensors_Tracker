package com.Course.DesignPatterns;

import android.app.Activity;
import android.widget.TextView;

public class TextviewStrategy implements IPresentStrategy {
    private Activity activity;
    private AppStatus appStatus;

    public TextviewStrategy(Activity activity) {
        this.activity = activity;
        this.appStatus = new AppStatus(activity);
    }

    @Override
    public void present(String data, Constants.SensorType sensorType) {
        TextView textView;

        switch (sensorType) {
            case LIGHT:
                textView = activity.findViewById(R.id.txtLight);
                break;
            case PRESSURE:
                textView = activity.findViewById(R.id.txtPressure);
                break;
            case PROXIMITY:
                textView = activity.findViewById(R.id.txtProximity);
                break;
            case POSITION:
                textView = activity.findViewById(R.id.txtPosition);
                break;
            case COMPASS:
                textView = activity.findViewById(R.id.txtCompass);
                break;
            default:
                return;
        }
        try {
            textView.setText(data);
            appStatus.setCurrentTime();
        } catch (Exception e) {
            textView.setText(String.format(Constants.EXCEPTION_PATTERN, e.getMessage()));
            appStatus.setStatus(String.format(Constants.ERROR_PATTERN, sensorType));
        }
    }
}
