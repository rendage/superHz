package com.lhs.kuaiyou.function;

/**
 * @description 条件函数
 * @author  renxz
 * @date 2022/04/29 15:09
 */
@FunctionalInterface
public interface IfConditionFunction {
    /**
     * 根据条件进行选择
     * @param trueExecute 条件为true处理
     * @param falseExecute 条件为false处理
     */
    void trueOrFalse(Runnable trueExecute,Runnable falseExecute);

}
