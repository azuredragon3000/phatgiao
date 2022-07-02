package com.example.quizphatgiao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FunctionCommon {

    public static String getToday() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getSecond() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }
    public static String Encode2(String encode) {
        encode = encode.replaceAll("\\.","");
        encode = encode.replaceAll("#","");
        encode = encode.replaceAll("\\$","");
        encode = encode.replaceAll("\\[","");
        encode = encode.replaceAll("]","");
        return encode;
    }

    public static void showDialogInform(String title, String content, Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(content);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    /*public static void MediationProcess(Context context) {
        MobileAds.initialize(context, initializationStatus -> {
            Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
            for (String adapterClass : statusMap.keySet()) {
                AdapterStatus status = statusMap.get(adapterClass);
                Log.d("MyApp", String.format(
                        "Adapter name: %s, Description: %s, Latency: %d",
                        adapterClass, status.getDescription(), status.getLatency()));
            }
        });
    }*/

    /*public static int convertAmlichV2(int date, int mon, int namsinh) {
        double TZ = 7.0;
        int start = VietCalendar.jdFromDate(date, mon, namsinh);
        int step = 15;
        int count = -1;
        int ret = 0;
        while (count++ < 240) {
            int jd = start + step * count;
            int[] s = VietCalendar.jdToDate(jd);
            int[] l = VietCalendar.convertSolar2Lunar(s[0], s[1], s[2], TZ);
            int[] s2 = VietCalendar.convertLunar2Solar(l[0], l[1], l[2], l[3], TZ);
            if (s[0] == s2[0] && s[1] == s2[1] && s[2] == s2[2]) {
                ret = l[2] + l[3];
                break;
            } else {
                ret = 0;
                break;
            }
        }
        return ret;
    }*/

    /*public static String convertAmlich(int date, int mon, int namsinh, String gender) {
        double TZ = 7.0;
        int start = VietCalendar.jdFromDate(date, mon, namsinh);
        int step = 15;
        int count = -1;
        String retr = "";
        while (count++ < 240) {
            int jd = start + step * count;
            int[] s = VietCalendar.jdToDate(jd);
            int[] l = VietCalendar.convertSolar2Lunar(s[0], s[1], s[2], TZ);
            int[] s2 = VietCalendar.convertLunar2Solar(l[0], l[1], l[2], l[3], TZ);
            if (s[0] == s2[0] && s[1] == s2[1] && s[2] == s2[2]) {
                retr = retr+"you "+gender+" borned "+s[0] + "/" + s[1] + "/" + s[2]+" with negative date: "+l[0] + "/" + l[1] + "/" + l[2];
                break;
            } else {
                retr = retr+"error";
                break;
            }
        }
        return retr;
    }*/

    public static int getRandom(int max, int min) {
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public static String[] convertToArrayString(List<String> list) {
        int len = list.size();
        String[] ret = new String[len];
        int i=0;
        for(String s: list){
            ret[i] = s;
            i++;
        }
        return ret;
    }
}
