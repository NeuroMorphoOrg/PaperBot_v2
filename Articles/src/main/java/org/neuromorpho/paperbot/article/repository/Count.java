package org.neuromorpho.paperbot.article.repository;

public class Count {
    private Integer count;

    public Count() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Count{" +
                "count=" + count +
                '}';
    }
}
