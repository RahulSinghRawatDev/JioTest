package com.example.jiotest.ViewModel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.jiotest.model.Task;
import com.example.jiotest.repository.MainRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    private MainRepository repository = new MainRepository(getApplication());

    private LiveData<List<Task>> data;

    public LiveData<List<Task>> getData() {
        return data;
    }

    public void init() {
        data = repository.getTaskData();
    }
}
