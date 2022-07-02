package utils;

/**
 * @param <I>
 * @param <O>
 * @author renxz
 * @description 管道
 * @date 2022/05/11 11:59
 */
public class Pipeline<I, O> {

    private IHandler<I, O> currHandle;

    public <K> Pipeline<I, K> andThen(IHandler<O, K> nextHandle) {
        return Pipeline.builder().handle(input -> nextHandle.process(currHandle.process((I) input))).build();
    }

    public O execute(I input) {
        return currHandle.process(input);
    }

    private Pipeline() {
    }

    private Pipeline(Builder builder) {
        this.currHandle = builder.currHandle;
    }

    public IHandler<I, O> getHandle() {
        return currHandle;
    }

    public void setHandle(IHandler<I, O> currHandle) {
        this.currHandle = currHandle;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder<I, O> {
        private IHandler<I, O> currHandle;

        public Builder() {
        }

        public Builder handle(IHandler<I, O> currHandle) {
            this.currHandle = currHandle;
            return this;
        }

        public Pipeline<I, O> build() {
            Pipeline<I, O> pipeline = new Pipeline<>(this);
            return pipeline;
        }

    }
}
