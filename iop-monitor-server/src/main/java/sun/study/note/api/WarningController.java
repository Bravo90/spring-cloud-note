package sun.study.note.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.CronTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.base.BaseResult;
import sun.study.note.task.ScheduledTask;
import sun.study.note.task.TaskRegistrar;
import sun.study.note.task.job.CronJob;
import sun.study.note.taskjob.PrintDateCronJob;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * WarningController:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */
@RestController
public class WarningController {


    @Autowired
    private TaskRegistrar taskRegistrar;

    private static final String exp = "0/1 * * * * *";

    @GetMapping("/waring")
    public BaseResult warning() throws InterruptedException {
        ScheduledTask job1 = taskRegistrar.addCronTask(new PrintDateCronJob("job1", exp));

        Thread.sleep(5000);
        System.out.println(job1.cancel());
        return BaseResult.successMsg(job1.toString());
    }

}
