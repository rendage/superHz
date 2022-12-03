package utils;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author renxz
 * @description function 函数 工具类
 * @date 2022/04/29 15:14
 */
public class SelfFunctionHelper {

    public static <T> void isNotNull(Supplier<T> tSupplier,Consumer<T> consumer){
        if (tSupplier.get() != null) {
            consumer.accept(tSupplier.get());
        }
    }
    public static <T> void isNotNull(T t, Consumer<T> consumer){
        if (t != null) {
            consumer.accept(t);
        }
    }
    public static void isNotBlank(Supplier<String> tSupplier,Consumer<String> consumer){
        if (tSupplier.get() != null && !"".equals(tSupplier.get())) {
            consumer.accept(tSupplier.get());
        }
    }
    public static void isNotBlank(String param, Consumer<String> consumer){
        if (param != null && !"".equals(param)) {
            consumer.accept(param);
        }
    }
    public static ThrowExceptionFunction isBlank(String param) {
        return (message) -> {
            if (param == null || "".equals(param)) {
                throw new RuntimeException(message);
            }
        };
    }

    public static ThrowExceptionFunction isNull(Object param) {
        return (message) -> {
            if (param == null) {
                throw new RuntimeException(message);
            }
        };
    }

    public static ThrowExceptionFunction isFalse(boolean b) {
        return (message) -> {
            if (!b) {
                throw new RuntimeException(message);
            }
        };
    }

    public static <T> ThrowExceptionFunction isFalse(T t, Predicate<T> condition) {
        return (message) -> {
            if (!condition.test(t)) {
                throw new RuntimeException(message);
            }
        };
    }


    public static <T> IfConditionFunction ifCondition(boolean b) {
        return (b1, b2) -> {
            if (b) {
                b1.run();
            } else {
                b2.run();
            }
        };

    }
/*    public static <T> IfCondition2Function ifConditionReturn(boolean b) {
        return (b1, b2) -> {
            if (b) {
                b1.get();
            } else {
                b2.get();
            }

        };

    }*/


}
