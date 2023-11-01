package cn.bugstack.chatglm;

import cn.bugstack.chatglm.model.CharInvokeCompletionResponse;
import cn.bugstack.chatglm.model.ChatCompletionRequest;
import cn.bugstack.chatglm.model.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author 小傅哥，微信：fustack
 * @description OpenAi 接口，用于扩展通用类服务
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IOpenAiApi {

    String v3_completions = "api/paas/v3/model-api/{model}/sse-invoke";

    /**
     * SSE请求接口
     * @param model
     * @param chatCompletionRequest
     * @return
     */
    @POST(v3_completions)
    Single<ChatCompletionResponse> completions(@Path("model") String model, @Body ChatCompletionRequest chatCompletionRequest);

    String async_completions = "api/paas/v3/model-api/{model}/async-invoke";

    /**
     * async请求接口
     * @param model  未完善
     * @param chatCompletionRequest
     * @return
     */
    @POST(async_completions)
    ChatCompletionResponse completionsAsync(@Path("model") String model, @Body ChatCompletionRequest chatCompletionRequest);

    String invoke_completions = "api/paas/v3/model-api/{model}/invoke";

    /**
     * 同步请求接口
     * @param model
     * @param chatCompletionRequest
     * @return
     */
    @POST(invoke_completions)
    Single<CharInvokeCompletionResponse> completionsInvoke(@Path("model") String model, @Body ChatCompletionRequest chatCompletionRequest);


}
