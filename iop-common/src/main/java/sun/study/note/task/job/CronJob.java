package sun.study.note.task.job;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Job
 *
 * @author sunzhen
 * @date 2019/7/1 16:16
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class CronJob implements Job, Runnable {

    private String jobName;
    private String cronExp;

    /**
     * 具体执行内容
     */
    public abstract void execute();

    @Override
    public void run() {
        execute();
    }
}
