package sun.study.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public String warnService() {
        String result = restTemplate.getForObject("http://monitor-server/waring", String.class);
        return result;
    }
}
