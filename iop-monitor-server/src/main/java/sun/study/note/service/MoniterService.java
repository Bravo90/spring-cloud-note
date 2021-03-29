package sun.study.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import sun.study.note.base.BaseResult;

/**
 * DemoService:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */

@FeignClient(name = "monitor-server")
public interface MoniterService {

    @GetMapping("/waring")
    BaseResult warning();

}
