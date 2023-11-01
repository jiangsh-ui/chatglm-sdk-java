package cn.bugstack.chatglm.model.invoke;

import java.util.List;

/**
 * @author jiang_sh
 * @date 31/10/2023
 * @Description
 */

public class InvokeResponse {
    private List<Choices> choices;
    private String request_id;
    private String task_id;
    private String task_status;
    private Usage usage;

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}
