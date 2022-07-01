package com.lhs.kuaiyou.function;

/**
 * @Descrition 异常
 * @author renxz
 * @date 2022/04/29 14:48
 */
@FunctionalInterface
public interface ThrowExceptionFunction {
    /**
     * throw exception
     * @param message
     */
    void throwException(String message);
}
