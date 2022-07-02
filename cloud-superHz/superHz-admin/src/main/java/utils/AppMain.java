package utils;

public class AppMain {
    public static void main(String[] args) {
        OneHandler oneHandler = new OneHandler();
        TwoHandler twoHandler = new TwoHandler();
        ThreeHandler threeHandler = new ThreeHandler();
        Object execute = Pipeline.builder().handle(oneHandler).build().andThen(twoHandler).andThen(threeHandler).execute("1234");
        oneHandler.then().process("1");

    }
}
