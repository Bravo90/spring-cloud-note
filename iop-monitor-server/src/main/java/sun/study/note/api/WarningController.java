package sun.study.note.api;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.base.BaseResult;
import sun.study.note.hytrix.WarningHystrix;
import sun.study.note.task.ScheduledTask;
import sun.study.note.task.TaskRegistrar;
import sun.study.note.taskjob.PrintDateCronJob;


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
    @HystrixCommand(fallbackMethod = "fallWay1",
            threadPoolKey = "t1",
            groupKey = "testGroup",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "5")
            })
    public BaseResult warning() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " : warning...");
        return BaseResult.successMsg("warning success！");
    }

    @GetMapping("/waring2")
    @HystrixCommand(fallbackMethod = "fallWay2",
            threadPoolKey = "t1",
            groupKey = "testGroup",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "550")
            })
    public BaseResult warning2() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("----------------------" + Thread.currentThread().getName() + " : warning...");
        return BaseResult.successMsg("warning2 success！");
    }

    @GetMapping("/waring3")
    @HystrixCommand(fallbackMethod = "fallWay3",
            threadPoolKey = "t3",
            groupKey = "testGroup",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "2")
            })
    public BaseResult warning3() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("------------------------------------------" + Thread.currentThread().getName() + " : warning...");
        return BaseResult.successMsg("warning3 success！");
    }


    @GetMapping("/waring4")
    public BaseResult warning4() {

        com.netflix.hystrix.HystrixCommand.Setter setter =
                com.netflix.hystrix.HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("abx"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("methodA"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("t4"))
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(2));

        WarningHystrix warning = new WarningHystrix(setter);
        warning.execute();
        return BaseResult.successMsg("warning4 success！");
    }

    @GetMapping("/scheduling")
    public BaseResult scheduling() throws InterruptedException {
        ScheduledTask job1 = taskRegistrar.addCronTask(new PrintDateCronJob("job1", exp));
        Thread.sleep(5000);
        System.out.println(job1.cancel());
        return BaseResult.successMsg(job1.toString());
    }

    public BaseResult fallWay1() {
        System.out.println("降级熔断1");
        return BaseResult.error("降级熔断");
    }

    public BaseResult fallWay2() {
        System.out.println("降级熔断2");
        return BaseResult.error("降级熔断");
    }

    public BaseResult fallWay3() {
        System.out.println("降级熔断3");
        return BaseResult.error("降级熔断");
    }
}
