package com.myapp.quizphatgiao;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdsBanner {

    public AdsBanner(Activity view, Context context, int adView){
        MobileAds.initialize(context, initializationStatus -> {
        });
        AdView mAdView = view.findViewById(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
