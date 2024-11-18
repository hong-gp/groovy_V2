package groovy.musicreview.api;

import lombok.Getter;

@Getter
public class Result<T> {

    private int count;
    private final T data;

    public Result(int count, T data) {
        this.count = count;
        this.data = data;
    }
}
