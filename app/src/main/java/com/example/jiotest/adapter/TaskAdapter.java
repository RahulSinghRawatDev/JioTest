package com.example.jiotest.adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jiotest.R;
import com.example.jiotest.databinding.TaskitemBinding;
import com.example.jiotest.model.Task;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList){
      this.taskList = taskList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskitemBinding binding =  DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.taskitem, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task dataModel = taskList.get(position);
        holder.bind(dataModel);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TaskitemBinding taskBinding;

        ViewHolder(TaskitemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.taskBinding = itemRowBinding;
        }

        void bind(Object obj) {
            taskBinding.setVariable(com.example.jiotest.BR.model, obj);
            taskBinding.executePendingBindings();
        }
    }
}
