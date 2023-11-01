package cn.bugstack.chatglm.test;

import cn.bugstack.chatglm.model.CharInvokeCompletionResponse;
import cn.bugstack.chatglm.model.ChatCompletionRequest;
import cn.bugstack.chatglm.model.Model;
import cn.bugstack.chatglm.model.Role;
import cn.bugstack.chatglm.session.Configuration;
import cn.bugstack.chatglm.session.OpenAiSession;
import cn.bugstack.chatglm.session.OpenAiSessionFactory;
import cn.bugstack.chatglm.session.defaults.DefaultOpenAiSessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author jiang_sh
 * @date 31/10/2023
 * @Description
 */
public class InvokeTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {
        // 1. 配置文件
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://open.bigmodel.cn/");
        configuration.setApiSecretKey("0a83dd04acf1f898c3b8572e5175d4e2.yegihw6F3OBaS9O8");
        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 3. 开启会话
        this.openAiSession = factory.openSession();
    }
    @Test
    public void testInvoke(){
        ChatCompletionRequest build = ChatCompletionRequest.builder()
                .model(Model.CHATGLM_TURBO).prompt(new ArrayList<ChatCompletionRequest.Prompt>() {
                    private static final long serialVersionUID = -7988151926241837899L;

                    {
                        add(ChatCompletionRequest.Prompt.builder()
                                .role(Role.user.getCode())
                                .content("jshnb")
                                .build());
                    }
                }).build();
        CharInvokeCompletionResponse response = openAiSession.completionInvoke(build);
        StringBuffer buffer = new StringBuffer();
        response.getData().getChoices().forEach(e->{
            buffer.append(e.getContent());
        });
        System.out.println("结果为"+buffer);
    }

}
