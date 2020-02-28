package com.Course.DesignPatterns;

public class Constants {
    public static final int REQUEST_CODE = 1;
    public static final int ERROR_VALUE = -1;
    public static double RADS_TO_DEGS = 180 / Math.PI;
    public static int MATRIX_DIM = 9;
    public static int VECTOR_DIM = 3;
    public static String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static String PRESENT_PATTERN = "%.2f";
    public static String LOCATION_PATTERN = "Lat: %.2f E  Long: %.2f N";
    public static String EXCEPTION_PATTERN = "Exception info: %s";
    public static String ERROR_PATTERN = "Error occurred in %s";
    public static long MIN_TIME_UPDATE = 2000;
    public static float MIN_DISTANCE_UPDATE = 1;
    public enum SensorType {LIGHT, PRESSURE, PROXIMITY, POSITION, COMPASS}
}
