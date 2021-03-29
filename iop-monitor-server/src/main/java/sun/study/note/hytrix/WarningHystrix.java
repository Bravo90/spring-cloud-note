package sun.study.note.hytrix;

import com.netflix.hystrix.HystrixCommand;
import sun.study.note.base.BaseResult;

/**
 * WarningHystrix:
 *
 * @author: sunzhen
 * Date: 2021-03-29
 */
public class WarningHystrix extends HystrixCommand<BaseResult> {

    public WarningHystrix(Setter setter) {
        super(setter);
    }


    @Override
    protected BaseResult run() throws Exception {
        System.out.println("------------------------------------------" + Thread.currentThread().getName() + " : warning...");
        return BaseResult.successMsg("warning4 success！");
    }

    @Override
    protected BaseResult getFallback() {
        System.out.println("降级熔断4");
        return BaseResult.error("降级熔断");
    }
}
