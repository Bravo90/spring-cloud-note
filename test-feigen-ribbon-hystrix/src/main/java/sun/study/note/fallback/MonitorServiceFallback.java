package sun.study.note.fallback;

import org.springframework.stereotype.Component;
import sun.study.note.base.BaseResult;
import sun.study.note.feign.MonitorService;

/**
 * MonitorServiceFallback:
 *
 * @author: sunzhen
 * Date: 2021-03-30
 */
@Component
public class MonitorServiceFallback implements MonitorService {

    @Override
    public BaseResult getMsg() {
        System.err.println("超时熔断");
        return BaseResult.error("getMsg(),超时熔断");
    }

    @Override
    public BaseResult getData() {
        System.err.println("超时熔断");
        return BaseResult.error("getData(),超时熔断");
    }

    @Override
    public String getString(int code) {
        System.err.println("超时熔断");
        return code + "getString(),超时熔断";
    }
}
