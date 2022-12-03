package utils;

/**
 * @author renxz
 * @date 2022/05/11 11:09
 * @param <I>
 * @param <O>
 */
@FunctionalInterface
public interface IHandler<I,O> {
    /**
     * 执行
     * @param input
     * @return
     */
    O process(I input);

}
