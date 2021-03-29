package sun.study.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.study.note.base.BaseResult;

/**
 * DemoService:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */
@Service
public class DemoService {
    @Autowired
    private RestTemplate restTemplate;

    public BaseResult warnService() {
        BaseResult result = restTemplate.getForObject("http://monitor-server/waring", BaseResult.class);
        return result;
    }
}
