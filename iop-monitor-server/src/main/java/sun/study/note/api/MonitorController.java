package sun.study.note.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.base.BaseResult;
import sun.study.note.pojo.WarningMsg;

/**
 * MonitorController:
 *
 * @author: sunzhen
 * Date: 2021-03-30
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/getMsg")
    public BaseResult getMsg() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return BaseResult.successMsg("getMsg()调用成功！端口号：" + port);
    }

    @GetMapping("/getData")
    public BaseResult getData() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        WarningMsg msg = new WarningMsg();
        msg.setMsgId(1);
        msg.setMsgCode(400);
        msg.setMsg("Error:错单量异常");
        msg.setDesc("错单类相关异常！");
        return BaseResult.success(msg, "getData()调用成功！端口号：" + port);
    }

    @GetMapping("/getString")
    public BaseResult getString(@RequestParam("code") int code) {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return BaseResult.successMsg("code = " + code + ",getString()调用成功！端口号：" + port);
    }
}
