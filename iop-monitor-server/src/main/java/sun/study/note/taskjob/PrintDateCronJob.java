package sun.study.note.taskjob;

import sun.study.note.task.job.CronJob;

import java.util.Date;

/**
 * PrintDateCronJob:
 *
 * @author: sunzhen
 * Date: 2021-03-27
 */
public class PrintDateCronJob extends CronJob {

    public PrintDateCronJob(String jobName, String cronExp) {
        super(jobName, cronExp);
    }

    @Override
    public void execute() {
        System.out.println(new Date());
    }
}
