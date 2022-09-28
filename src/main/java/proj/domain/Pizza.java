package proj.domain;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.*;

@Entity
public class Pizza implements Comparable<Pizza>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "pizza_product",joinColumns = @JoinColumn(name = "pizza_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    @SortNatural
    private SortedSet<Product> productList = new TreeSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SortedSet<Product> getProductList() {
        return productList;
    }

    public void setProductList(SortedSet<Product> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        addProduct(product,false);
    }
    public void addProduct(Product product, boolean otherSideHasBeenSet) {
        this.getProductList().add(product);
        if(otherSideHasBeenSet) {
            return;
        }
        product.addPizza(this,true);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" + productList +
                '}';
    }

    @Override
    public int compareTo(Pizza o) {
        return (int) (this.id-o.id);
    }
}
