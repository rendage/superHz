package com.lhs.kuaiyou.function.pipeline;

public class OneHandler implements IHandler<String,A1>{

    @Override
    public A1 process(String input) {
        System.out.println("this is oneHandler ...");
        return new A1("a1");
    }

    public IHandler then(){
        return (p) ->{
            return this.process((String) p);
        };
    }
}
