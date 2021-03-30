package sun.study.note.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * WarningMsg:
 *
 * @author: sunzhen
 * Date: 2021-03-30
 */
@Getter
@Setter
public class WarningMsg {
    private int msgId;
    private int msgCode;
    private String msg;
    private String desc;
}
