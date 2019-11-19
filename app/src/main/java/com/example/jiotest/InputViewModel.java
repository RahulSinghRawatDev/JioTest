package com.example.jiotest;
import android.app.Application;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.jiotest.model.Task;
import com.example.jiotest.repository.MainRepository;

public class InputViewModel extends AndroidViewModel {

    private MainRepository repository;
    private ObservableField<String> firstName = new ObservableField<>();
    private ObservableField<String> lastName = new ObservableField<>();
    private ObservableField<String> mobileNumber = new ObservableField<>();
    private ObservableField<String> email = new ObservableField<>();
    private ObservableField<String> amount = new ObservableField<>();
    private MutableLiveData<String> saveMsg;
    private MutableLiveData<String> validationMsg;

    public LiveData<String> getSaveMsg() {
        return saveMsg;
    }

    public InputViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repository = new MainRepository(getApplication());
        validationMsg = new MutableLiveData<>();
        saveMsg = repository.getSaveMsg();
    }

    public ObservableField<String> getFirstName() {
        return firstName;
    }

    public ObservableField<String> getLastName() {
        return lastName;
    }

    public ObservableField<String> getMobileNumber() {
        return mobileNumber;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getAmount() {
        return amount;
    }

    public void save() {
        if (!TextUtils.isEmpty(getFirstName().get())) {
            if (!TextUtils.isEmpty(getLastName().get())) {
                if (!TextUtils.isEmpty(getMobileNumber().get())) {
                    if (getMobileNumber().get().length()==10) {
                        if (!TextUtils.isEmpty(getEmail().get())) {
                            saveUser();
                        } else {
                            validationMsg.setValue(getApplication().getResources().getString(R.string.email_validation));
                        }

                    } else {
                        validationMsg.setValue(getApplication().getResources().getString(R.string.mobileten_validation));
                    }

                } else {
                    validationMsg.setValue(getApplication().getResources().getString(R.string.mobile_validation));

                }
            } else {
                validationMsg.setValue(getApplication().getResources().getString(R.string.lastname_validation));
            }
        } else {
            validationMsg.setValue(getApplication().getResources().getString(R.string.firstname_validation));
        }
    }

    private void saveUser() {
        repository.addUser(new Task(getFirstName().get(), getLastName().get(), getMobileNumber().get(),
                getEmail().get(), getAmount().get()));

    }

    public LiveData<String> getValidationMsg() {
        return validationMsg;
    }

}
