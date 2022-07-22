package com.myapp.quizphatgiao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModelSecondFragment extends ViewModel {
    private final MutableLiveData<PassFailed> selected = new MutableLiveData<>();

    public void select(PassFailed item) {
        selected.setValue(item);
    }

    public LiveData<PassFailed> getSelected() {
        return selected;
    }
}
