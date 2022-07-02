package com.example.quizphatgiao;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quizphatgiao.databinding.FragmentGameFirstBinding;

public class FirstFragment extends Fragment  {

    private FragmentGameFirstBinding binding;
    ShareViewModelFirstFragment modelFirstFragment;
    ShareViewModelSecondFragment modelSecondFragment;
    ShareViewModelSecondFragmentFailed modelSecondFragmentFailed;
    DatabasePhatGiao databasePhatGiao;
    int curLevel;
    boolean clicked;
    //Question[] questions;
    Question currentQuestion;
    int num;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        clicked = false;



        //questions = getQuestion();
        num = 0;
        binding = FragmentGameFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /*private Question[] getQuestion() {
       *//* Question[] questions = new Question[5];
        questions[0] = new Question("question1","a1","b1","c1","d1",1);
        questions[1] = new Question("question2","a2","b2","c2","d2",1);
        questions[2] = new Question("question3","a3","b3","c3","d3",1);
        questions[3] = new Question("question4","a4","b4","c4","d4",1);
        questions[4] = new Question("question5","a5","b5","c5","d5",1);*//*
        return questions;
    }*/

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        modelFirstFragment = new ViewModelProvider(requireActivity()).get(ShareViewModelFirstFragment.class);
        modelSecondFragment = new ViewModelProvider(requireActivity()).get(ShareViewModelSecondFragment.class);
        modelSecondFragmentFailed =
                new ViewModelProvider(requireActivity()).get(ShareViewModelSecondFragmentFailed.class);
        databasePhatGiao = ((SubApp) getActivity().getApplication()).getDatabasePhatGiao();
        currentQuestion = getRandomQuestion(); //questions[0];
        showQuestion(currentQuestion);
        modelFirstFragment.getSelected().observe(getViewLifecycleOwner(), item -> {
            // get content data
            curLevel = item;
            binding.level.setText("level "+item);

        });

        binding.ans1.setOnClickListener(v->{
            showDialog(checkResult(currentQuestion, 1));
        });
        binding.ans2.setOnClickListener(v->{
            showDialog(checkResult(currentQuestion, 2));
        });
        binding.ans3.setOnClickListener(v->{
            showDialog(checkResult(currentQuestion, 3));
        });
        binding.ans4.setOnClickListener(v->{
            showDialog(checkResult(currentQuestion, 4));
        });

    }

    private Question getRandomQuestion() {

        int random = FunctionCommon.getRandom(619,0);
        Question question = databasePhatGiao.getQuestion(random);
        return  question;
    }

    private boolean checkResult(Question currentQuestion, int i) {
        return (currentQuestion.result == i);
    }

    private void showQuestion(Question currentQuestion) {
        binding.question.setText(currentQuestion.question);
        binding.ans1.setText(currentQuestion.ans1);
        binding.ans2.setText(currentQuestion.ans2);
        binding.ans3.setText(currentQuestion.ans3);
        binding.ans4.setText(currentQuestion.ans4);
    }

    private void showDialog(boolean rs) {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Are You Sure");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sure",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //move to next activity
                        if(rs){
                            dialog.dismiss();
                            showDialog2();
                        }else{
                            showDialog3();
                            dialog.dismiss();
                        }
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Not Sure",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                       // clicked = false;
                    }
                });
        alertDialog.show();
    }

    private void showDialog3() {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Rất Tiếc bạn đả thua, Cố gắng thử lại nhé");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment);
                        // clicked = false;
                        modelSecondFragment.select(new PassFailed(curLevel,false));
                    }
                });
        alertDialog.show();
    }

    private void showDialog2() {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Chúc Mừng Bạn trả lời chính xác");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        num++;
                        binding.numQuestion.setText("Question "+num);
                        binding.clock.setText("Điểm "+num);
                        if(num < 5){
                            loadNextQuestion();
                        }else{
                            showDialog4();
                            //loadNextLevel();
                        }
                        //move to next activity
                        dialog.dismiss();
                        //clicked = false;
                    }
                });
        alertDialog.show();
    }

    private void showDialog4() {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Chúc Mừng Bạn vượt qua level mới");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        loadNextLevel();
                    }
                });
        alertDialog.show();
    }

    private void loadNextQuestion() {

        currentQuestion = getRandomQuestion(); // questions[num];
        showQuestion(currentQuestion);
    }

    private void loadNextLevel() {
        curLevel++;
        // return next level if winn
        modelSecondFragment.select(new PassFailed(curLevel,true));
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}