package sun.study.note.apiclient.hysrtix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * HystrixCommandFactory:
 *
 * @author: sunzhen
 * Date: 2021-03-31
 */
public class HystrixCommandFactory {

    public <R> HystrixCommand<R> createCommand(R r) {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("serviceA"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("methodA"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100000));

        HystrixCommand<R> command = new HystrixCommand<R>(setter) {
            @Override
            protected R run() throws Exception {
                System.out.println();
                System.out.println("HystrixCommand执行了！");
                return r;
            }


            @Override
            protected R getFallback() {
                System.out.println("熔断！");
                return null;
            }
        };

        return command;
    }
}
