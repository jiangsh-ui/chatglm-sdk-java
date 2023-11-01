package cn.bugstack.chatglm.model.invoke;

/**
 * @author jiang_sh
 * @date 31/10/2023
 * @Description
 */
public class Choices {
    private String role;
    private String content;
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

}
