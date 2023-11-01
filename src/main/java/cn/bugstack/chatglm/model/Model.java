package cn.bugstack.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话模型
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Getter
@AllArgsConstructor
public enum Model {

    CHATGLM_6B_SSE("chatGLM_6b_SSE", "ChatGLM-6B 测试模型"),
    CHATGLM_LITE("chatglm_lite", "轻量版模型，适用对推理速度和成本敏感的场景"),
    CHATGLM_LITE_32K("chatglm_lite_32k", "标准版模型，适用兼顾效果和成本的场景"),
    CHATGLM_STD("chatglm_std", "适用于对知识量、推理能力、创造力要求较高的场景"),
    CHATGLM_PRO("chatglm_pro", "适用于对知识量、推理能力、创造力要求较高的场景"),
    CHATGLM_TURBO("chatglm_turbo","根据输入的自然语言指令完成多种语言类任务"),

    ;
    private final String code;
    private final String info;
}
