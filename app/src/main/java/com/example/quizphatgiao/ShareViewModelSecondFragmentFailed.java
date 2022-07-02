package com.example.quizphatgiao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModelSecondFragmentFailed extends ViewModel {
    private final MutableLiveData<Integer> selected = new MutableLiveData<>();

    public void select(Integer item) {
        selected.setValue(item);
    }

    public LiveData<Integer> getSelected() {
        return selected;
    }
}
