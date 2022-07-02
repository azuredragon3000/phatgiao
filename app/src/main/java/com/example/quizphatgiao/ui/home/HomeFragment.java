package com.example.quizphatgiao.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizphatgiao.AdsInterstitial;
import com.example.quizphatgiao.GameActivity;
import com.example.quizphatgiao.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    AdsInterstitial adsInterstitial;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        adsInterstitial = new AdsInterstitial(
                "07CC7E40850ABA2DF210A2D2564CAD76",
                "ca-app-pub-8404443559572571/9913894405",
                getContext());

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //final TextView textView = binding.textHome;
        binding.stargame.setOnClickListener(v->{
            // move to quiz phat giao activity
            Intent intent = new Intent(requireActivity(), GameActivity.class);
            startActivity(intent);
            adsInterstitial.showAds(requireActivity());
        });

        /*binding.truyenphatgiao.setOnClickListener(v->{
            // move to truyenphatgiao activity
            //Intent intent = new Intent(requireActivity(), TruyenPhatGiaoActivity.class);
            //startActivity(intent);
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}