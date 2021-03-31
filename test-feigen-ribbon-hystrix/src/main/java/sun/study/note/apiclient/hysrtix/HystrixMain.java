package sun.study.note.apiclient.hysrtix;

import com.netflix.hystrix.HystrixCommand;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observables.BlockingObservable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * HystrixMain:
 *
 * @author: sunzhen
 * Date: 2021-03-31
 */
public class HystrixMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        HystrixCommandFactory factory = new HystrixCommandFactory();
        HystrixCommand<String> command = factory.createCommand("hello!");
        Observable<String> observable = command.toObservable();
        observable.single().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complate");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("s = " + s);
            }
        });

        Thread.sleep(1000);

    }


    private static void test1() {
        HystrixCommandFactory factory = new HystrixCommandFactory();
        HystrixCommand<String> command = factory.createCommand("hello!");
        String execute = command.execute();
        System.out.println(execute);
    }

    private static void test2() throws InterruptedException, ExecutionException {
        HystrixCommandFactory factory = new HystrixCommandFactory();
        HystrixCommand<String> command = factory.createCommand("hello!");
        Future<String> queue = command.queue();
        System.out.println("after queue");
        Thread.sleep(1000);
        String result = queue.get();
        System.out.println(result);
    }

    private static void test3() throws InterruptedException, ExecutionException {
        HystrixCommandFactory factory = new HystrixCommandFactory();
        HystrixCommand<String> command = factory.createCommand("hello!");
        Observable<String> observe = command.observe();
        Thread.sleep(1000);
    }

    private static void test4() throws InterruptedException, ExecutionException {
        HystrixCommandFactory factory = new HystrixCommandFactory();
        HystrixCommand<String> command = factory.createCommand("hello!");
        Observable<String> observe = command.toObservable();
        System.out.println("未订阅，command未执行！");
        Thread.sleep(1000);
        System.out.println("订阅，command执行！");
        Subscription subscribe = observe.subscribe();
        Thread.sleep(1000);
    }
}
