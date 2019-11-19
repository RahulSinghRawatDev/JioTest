package com.example.jiotest.repository;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.jiotest.db.DbHandler;
import com.example.jiotest.model.Task;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private MutableLiveData<List<Task>> taskData = new MutableLiveData<>();
    private MutableLiveData<String> saveMsg = new MutableLiveData<>();

    private Context context;

    public MainRepository(Context context){
        this.context = context;
        getTasks();
    }

    public MutableLiveData<String> getSaveMsg() {
        return saveMsg;
    }

    public LiveData<List<Task>> getTaskData() {
        return taskData;
    }

    private Maybe<List<Task>> getTaskList() {
        return DbHandler.getInstance(context).getAppDatabase().getTaskDao().getAllTask();
    }

    @SuppressLint("CheckResult")
    private void getTasks(){
        getTaskList().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) {
                        taskData.setValue(tasks);
                    }
                });
    }



    public void addUser(final Task task) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                addTask(task);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.i("log","task complete");
                saveMsg.setValue(task.getFirstName()+" Saved Successfully");
                getTasks();
            }

            @Override
            public void onError(Throwable e) {
                saveMsg.setValue("Not Saved Successfully");
                Log.i("log","task not complete"+e.getMessage());
            }
        });
    }


    private void addTask(Task task) {
        DbHandler.getInstance(context).getAppDatabase().getTaskDao().addTask(task);
    }
}
