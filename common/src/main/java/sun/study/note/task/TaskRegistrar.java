package sun.study.note.task;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;
import sun.study.note.task.job.CronJob;
import sun.study.note.task.job.Job;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * TaskRegistrar
 *
 * @author sunzhen
 * @date 2019/7/1 14:24
 */
@Component
public class TaskRegistrar implements DisposableBean {

    private final Map<String, ScheduledTask> tasks = new ConcurrentHashMap<>();

    @Autowired
    private TaskScheduler taskScheduler;


    public ScheduledTask addCronTask(CronJob job) {
        return addTask(job.getJobName(), new CronTask(job, job.getCronExp()));
    }


    private ScheduledTask addTask(String taskName, CronTask task) {
        if (!Objects.isNull(tasks.get(taskName))) {
            throw new RuntimeException("task named " + taskName + " is already exist!");
        }
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = taskScheduler.schedule(task.getRunnable(), task.getTrigger());
        tasks.put(taskName, scheduledTask);
        return scheduledTask;
    }

    public boolean remove(String taskName) {
        ScheduledTask task = tasks.remove(taskName);
        if (task != null && task.future != null) {
            return task.cancel();
        }
        return false;
    }

    public Map<String, ScheduledTask> getTasks() {
        return tasks;
    }

    @Override
    public void destroy() {
        for (String k : tasks.keySet()) {
            tasks.get(k).cancel();
        }
        tasks.clear();
    }
}
