package sun.study.note.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.study.note.base.BaseResult;
import sun.study.note.fallback.MonitorServiceFallback;

/**
 * DemoService:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */

@FeignClient(name = "monitor-server", fallback = MonitorServiceFallback.class)
public interface MonitorService {

    @RequestMapping(value = "monitor/getMsg", method = RequestMethod.GET)
    BaseResult getMsg();

    @RequestMapping(value = "monitor/getData", method = RequestMethod.GET)
    BaseResult getData();

    @RequestMapping(value = "monitor/getString", method = RequestMethod.GET)
    String getString(@RequestParam("code") int code);

}
