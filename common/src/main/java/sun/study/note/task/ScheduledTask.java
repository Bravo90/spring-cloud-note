package sun.study.note.task;

import java.util.concurrent.ScheduledFuture;

/**
 * ScheduledTask 封装执行结果
 *
 * @author sunzhen
 * @date 2019/7/1 13:37
 */
public class ScheduledTask {

    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public boolean cancel() {
        boolean cancel = false;
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            cancel = future.cancel(true);
        }
        return cancel;
    }
}
