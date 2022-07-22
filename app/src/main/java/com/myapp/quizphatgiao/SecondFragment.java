package com.myapp.quizphatgiao;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.myapp.quizphatgiao.databinding.FragmentGameSecondBinding;
import com.myapp.mylibrary.ads.AdsInterstitial;

public class SecondFragment extends Fragment  {

    private FragmentGameSecondBinding binding;
    ShareViewModelFirstFragment modelFirstFragment;
    ShareViewModelSecondFragment modelSecondFragment;
    ShareViewModelSecondFragmentFailed modelSecondFragmentFailed;
    int width,height;
    Level curLevel;
    Level[] setLevel;
    boolean animationlistener;
    boolean e_run;
    int duration;
    AdsInterstitial adsInterstitial;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adsInterstitial = new AdsInterstitial(
                "07CC7E40850ABA2DF210A2D2564CAD76",
                "ca-app-pub-8404443559572571/9913894405",
                requireContext());


        animationlistener = false;
        setLevel = new Level[25];
        setLevel[0] = new Level(50, 100, 1);
        setLevel[1] = new Level(150, 100, 2);
        setLevel[2] = new Level(250, 100, 3);
        setLevel[3] = new Level(350, 100, 4);
        setLevel[4] = new Level(450, 100, 5);

        setLevel[5] = new Level(50, 200, 6);
        setLevel[6] = new Level(150, 200, 7);
        setLevel[7] = new Level(250, 200, 8);
        setLevel[8] = new Level(350, 200, 9);
        setLevel[9] = new Level(450, 200, 10);

        setLevel[10] = new Level(50, 300, 11);
        setLevel[11] = new Level(150, 300, 12);
        setLevel[12] = new Level(250, 300, 13);
        setLevel[13] = new Level(350, 300, 14);
        setLevel[14] = new Level(450, 300, 15);

        setLevel[15] = new Level(50, 400, 16);
        setLevel[16] = new Level(150, 400, 17);
        setLevel[17] = new Level(250, 400, 18);
        setLevel[18] = new Level(350, 400, 19);
        setLevel[19] = new Level(450, 400, 20);

        setLevel[20] = new Level(50, 500, 21);
        setLevel[21] = new Level(150, 500, 22);
        setLevel[22] = new Level(250, 500, 23);
        setLevel[23] = new Level(350, 500, 24);
        setLevel[24] = new Level(450, 500, 25);

        curLevel = getLevel(setLevel[0]);
        e_run = false;
        duration = 4000;

    }

    private boolean getContinue() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getBoolean("CNT",false);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentGameSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        modelSecondFragment.getSelected().observe(getViewLifecycleOwner(), item -> {
            if(item.pass){
                int previous_item = item.level-1;
                for (Level level : setLevel) {
                    if (previous_item == level.level) {
                        curLevel = level;
                        break;
                    }
                }
                binding.level.setX(curLevel.x);
                binding.level.setY(curLevel.y);

                for (Level level : setLevel) {
                    if (item.level == level.level) {
                        curLevel = level;
                        break;
                    }
                }
                duration = 4000;
                binding.button.setVisibility(View.INVISIBLE);
            }else{
                for (Level level : setLevel) {
                    if (item.level == level.level) {
                        curLevel = level;
                        break;
                    }
                }
                binding.level.setX(curLevel.x);
                binding.level.setY(curLevel.y);
                duration = 0;
                binding.button.setVisibility(View.VISIBLE);
            }

        });

        ObjectAnimator animY = ObjectAnimator.ofFloat(binding.level, "translationY", curLevel.y);
        ObjectAnimator animX = ObjectAnimator.ofFloat(binding.level, "translationX", curLevel.x);

        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animY, animX);
        animSetXY.setDuration(duration);
        animSetXY.start();
        animSetXY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationlistener = true;
                binding.button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        updateLevel();
    }

    private void updateLevel() {
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setVisibility(View.INVISIBLE);
        modelFirstFragment = new ViewModelProvider(requireActivity()).get(ShareViewModelFirstFragment.class);
        modelSecondFragment = new ViewModelProvider(requireActivity()).get(ShareViewModelSecondFragment.class);
        modelSecondFragmentFailed =
                new ViewModelProvider(requireActivity()).get(ShareViewModelSecondFragmentFailed.class);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(view.getHeight() > 0 && view.getWidth() > 0) {
                    width = view.getWidth();
                    height = view.getHeight();
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

        binding.button.setOnClickListener(v->{
            if(animationlistener) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                modelFirstFragment.select(curLevel.level);

                adsInterstitial.showAds(requireActivity());

            }
        });

    }

    private Level getLevel(Level level) {
        return level;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}