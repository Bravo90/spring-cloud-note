package sun.study.note.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.service.DemoService;

/**
 * DemoController:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService  demoService;

    @GetMapping("/demo")
    public String demo(){
        return demoService.warnService();
    }

    @GetMapping("/demo2")
    public String demo2(){
        return "111111";
    }
}
