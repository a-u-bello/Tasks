package com.example.ahmed.simpdo.data.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Calendar;
import java.util.UUID;

import static com.example.ahmed.simpdo.utils.DBSchema.*;


/**
 * Created by ahmed on 8/24/17.
 */

public class TaskWrapper extends CursorWrapper {
    public TaskWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask(){
        String idString = getString(getColumnIndex(TASK_ID));
        String taskTitle = getString(getColumnIndex(TASK_TITLE));
        String taskDesc = getString(getColumnIndex(TASK_DESC));
        int taskDone = getInt(getColumnIndex(TASK_DONE));
        long taskDate = getLong(getColumnIndex(TASK_DATE));
        int alarmTime = getInt(getColumnIndex(TASK_ALARM_TIME));
        int repeatTask = getInt(getColumnIndex(TASK_REPEAT));
        int isRepeating = getInt(getColumnIndex(TASK_IS_REPEATING));

        Task task = new Task(UUID.fromString(idString));
        task.setTaskTitle(taskTitle);
        task.setTaskDesc(taskDesc);
        task.setDone(taskDone == 0);
        task.setRepeatCategory(repeatTask);
        task.setAlarmTime(alarmTime);
        task.setAlreadyRepeating(isRepeating == 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(taskDate);

        task.setTaskDate(calendar);
        return task;
    }
}
