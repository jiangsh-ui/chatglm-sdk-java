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

参照处：https://bugstack.cn/
/**
* @author jiang_sh
* @date 31/10/2023
* @Description  chatglm同步请求测试
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


package cn.bugstack.chatglm.test;

import cn.bugstack.chatglm.model.*;
import cn.bugstack.chatglm.session.Configuration;
import cn.bugstack.chatglm.session.OpenAiSession;
import cn.bugstack.chatglm.session.OpenAiSessionFactory;
import cn.bugstack.chatglm.session.defaults.DefaultOpenAiSessionFactory;
import cn.bugstack.chatglm.utils.BearerTokenUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


/**
* @author jiang_sh
* @date 31/10/2023
* @Description  chatglmSSE请求测试
  */
  @Slf4j
  public class ApiTest {

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

  /**
    * 流式对话
      */
      @Test
      public void test_completions() throws JsonProcessingException, InterruptedException {
      // 入参；模型、请求信息
      ChatCompletionRequest request = new ChatCompletionRequest();
      request.setModel(Model.CHATGLM_LITE); // chatGLM_6b_SSE、chatglm_lite、chatglm_lite_32k、chatglm_std、chatglm_pro
      request.setPrompt(new ArrayList<ChatCompletionRequest.Prompt>() {
      private static final long serialVersionUID = -7988151926241837899L;

           {
               add(ChatCompletionRequest.Prompt.builder()
                       .role(Role.user.getCode())
                       .content("写个java冒泡排序")
                       .build());
           }
      });

      // 请求
      openAiSession.completions(request, new EventSourceListener() {
      @Override
      public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
      ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
      log.info("测试结果 onEvent：{}", response.getData());
      // type 消息类型，add 增量，finish 结束，error 错误，interrupted 中断
      if (EventType.finish.getCode().equals(type)) {
      ChatCompletionResponse.Meta meta = JSON.parseObject(response.getMeta(), ChatCompletionResponse.Meta.class);
      log.info("[输出结束] Tokens {}", JSON.toJSONString(meta));
      }
      }

           @Override
           public void onClosed(EventSource eventSource) {
               log.info("对话完成");
           }

      });

      // 等待
      new CountDownLatch(1).await();
      }

  @Test
  public void test_curl() {
  // 1. 配置文件
  Configuration configuration = new Configuration();
  configuration.setApiHost("https://open.bigmodel.cn/");
  configuration.setApiSecretKey("4e087e4135306ef4a676f0cce3cee560.sgP2DUsWEVPxk0UI");

       // 2. 获取Token
       String token = BearerTokenUtils.getToken(configuration.getApiKey(), configuration.getApiSecret());
       log.info("1. 在智谱Ai官网，申请 ApiSeretKey 配置到此测试类中，替换 setApiSecretKey 值。 https://open.bigmodel.cn/usercenter/apikeys");
       log.info("2. 运行 test_curl 获取 token：{}", token);
       log.info("3. 将获得的 token 值，复制到 curl.sh 中，填写到 Authorization: Bearer 后面");
       log.info("4. 执行完步骤3以后，可以复制直接运行 curl.sh 文件，或者复制 curl.sh 文件内容到控制台/终端/ApiPost中运行");
  }

  public static void main(String[] args) throws JsonProcessingException, InterruptedException {
  // 1. 配置文件
  Configuration configuration = new Configuration();
  configuration.setApiHost("https://open.bigmodel.cn/");
  configuration.setApiSecretKey("0a83dd04acf1f898c3b8572e5175d4e2.yegihw6F3OBaS9O8");
  // 2. 会话工厂
  OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
  // 3. 开启会话
  OpenAiSession openAiSession = factory.openSession();
  ChatCompletionRequest build = ChatCompletionRequest.builder().model(Model.CHATGLM_LITE_32K).prompt(new ArrayList<>()).build();
  System.out.println("我是chatgpt,请输入你的问题");
  Scanner scanner = new Scanner(System.in);
  while (scanner.hasNextLine()){
  String line = scanner.nextLine();
  build.getPrompt().add(ChatCompletionRequest.Prompt.
  builder().role(Role.user.getCode()).content(line).build());
  EventSource eventSource = openAiSession.completions(build, new EventSourceListener() {
  @Override
  public void onEvent(EventSource eventSource, String id, String type, String data) {
  System.out.println(data);
  }

               @Override
               public void onFailure(EventSource eventSource, Throwable t, Response response) {
                   System.out.println(response.code());
               }

               @Override
               public void onClosed(EventSource eventSource) {
                   System.out.println("请输入你的问题：");
               }
           });
       }
       }
  }

