package utils;

import java.util.function.Supplier;

/**
 * @author renxz
 * @description 条件函数
 * @date 2022/04/29 15:37
 */
@FunctionalInterface
public interface IfCondition2Function {
    /**
     * 条件函数
     *
     * @param t1 返回为真
     * @param t2 返回为false
     * @param <T> 泛型
     * @return
     */
    <T> T trueOrFalse(Supplier<T> t1, Supplier<T> t2);
}
