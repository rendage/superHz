package com.lhs.kuaiyou.function.pipeline;

public class TwoHandler implements IHandler<A1,B1> {
    @Override
    public B1 process(A1 input) {
        System.out.println("this is TwoHandler ...");
        return new B1("b1");
    }
}
