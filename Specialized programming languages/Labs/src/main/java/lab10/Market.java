package lab10;

import java.util.*;

public class Market {
    private Map<String, Product> products;
    private Map<String, Integer> counts;

    public Market() {
        products = new HashMap<>();
        counts = new WeakHashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getArticle(), product);
        counts.merge(product.getArticle(), 1, Integer::sum);

    }

    public int getCount(String article) {
        return counts.get(article);
    }

    public Product getProduct(String article) {
        return products.get(article);
    }

    public Collection<Product> getProducts() {
        Collection<Product> collection = products.values();
        return Collections.unmodifiableCollection(collection);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(";" + System.lineSeparator(), "", "");
        for (Product p : products.values()) {
            stringJoiner.add(p.toString() + " count: " + counts.get(p.getArticle()).toString());
        }
        return stringJoiner.toString();
    }
}
