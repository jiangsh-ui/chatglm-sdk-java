package cn.bugstack.chatglm.model.invoke;

import java.util.List;

/**
 * @author jiang_sh
 * @date 31/10/2023
 * @Description
 */
public class Data {
    private String task_id;
    private String request_id;
    private String task_status;
    private List<Choices> choices;
    private Usage usage;
    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
    public String getTask_id() {
        return task_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
    public String getRequest_id() {
        return request_id;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }
    public String getTask_status() {
        return task_status;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
    public List<Choices> getChoices() {
        return choices;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public Usage getUsage() {
        return usage;
    }


}
