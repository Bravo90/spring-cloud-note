package sun.study.note.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.base.BaseResult;
import sun.study.note.service.DemoService;
import sun.study.note.service.MoniterService;

/**
 * DemoController:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private MoniterService moniterService;

    @GetMapping("/demo")
    public BaseResult demo() {
        return demoService.warnService();
    }

    @GetMapping("/demo2")
    public BaseResult demo2() {
        return moniterService.warning();
    }
}
