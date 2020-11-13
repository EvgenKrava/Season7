package lab10;

import java.io.Serializable;
import java.util.StringJoiner;

public class Product implements Comparable<Product>, Serializable {
    private final String article;
    private final int cost;
    private final int realize;

    public Product(String article, int cost, int realize) {
        this.article = article;
        this.cost = cost;
        this.realize = realize;
    }

    public String getArticle() {
        return article;
    }

    public int getCost() {
        return cost;
    }

    public int getRealize() {
        return realize;
    }

    @Override
    public int compareTo(Product o) {
        return realize - o.getRealize();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("article='" + article + "'")
                .add("cost=" + cost)
                .add("realize=" + realize)
                .toString();
    }
}
