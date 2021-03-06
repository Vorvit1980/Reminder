package com.example.vorvit1980.reminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vorvit1980.reminder.R;
import com.example.vorvit1980.reminder.Utils;
import com.example.vorvit1980.reminder.model.Item;
import com.example.vorvit1980.reminder.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vorvit1980 on 01.02.2017.
 */

public class CurrentTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> items = new ArrayList<>();

    private static final int TYPE_TASK = 0;
    private static final int TYPE_SEPARATOR = 1;

    public Item getItem(int position) {
        return items.get(position);
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItem(int location, Item item) {
        items.add(location, item);
        notifyItemInserted(location);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case TYPE_TASK:
                View v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.model_task, viewGroup, false);
                TextView title = (TextView) v.findViewById(R.id.tvTaskTitle);
                TextView date = (TextView) v.findViewById(R.id.tvTaskDate);

                return new TaskViewHolder(v, title, date);
            //case TYPE_SEPARATOR:
            //break;;
            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        Item item =items.get(position);

        if(item.isTask()){
            viewHolder.itemView.setEnabled(true);
            ModelTask task = (ModelTask) item;
            TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;

            taskViewHolder.title.setText(task.getTitle());
            if(task.getDate()!=0){
                taskViewHolder.date.setText(Utils.getFullDate(task.getDate()));
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (getItem(position).isTask()) {
            return TYPE_TASK;
        } else {
            return TYPE_SEPARATOR;
        }
    }

    private class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        public TaskViewHolder(View itemView, TextView title, TextView date) {
            super(itemView);
            this.title = title;
            this.date = date;
        }


    }

}
