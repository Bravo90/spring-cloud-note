package sun.study.note.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.base.BaseResult;
import sun.study.note.feign.MonitorService;

import java.util.Currency;

/**
 * DemoController:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/getMsg")
    public BaseResult getMsg() {
        return monitorService.getMsg();
    }

    @GetMapping("/getData")
    public BaseResult getData() {
        return monitorService.getData();
    }

    @GetMapping("/getString")
    public String getString(@RequestParam("code") int code) {
        return monitorService.getString(code);
    }

}
