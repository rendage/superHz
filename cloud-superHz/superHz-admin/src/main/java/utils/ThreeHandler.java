package com.lhs.kuaiyou.function.pipeline;

public class ThreeHandler implements IHandler<B1,C1> {
    @Override
    public C1 process(B1 input) {
        System.out.println("this is ThreeHandler ...");
        return new C1("c1");
    }


}
