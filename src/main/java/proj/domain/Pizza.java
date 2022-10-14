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
    private long cookingTimeInMin;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "pizza_product",joinColumns = @JoinColumn(name = "pizza_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    @SortNatural
    private SortedSet<Product> productList = new TreeSet<>();
    @ManyToMany(mappedBy = "orderedPizza",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

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


    public long getCookingTimeInMin() {
        return cookingTimeInMin;
    }

    public void setCookingTimeInMin(long cookingTimeInMin) {
        this.cookingTimeInMin = cookingTimeInMin;
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
    public int compareTo(Pizza o) {
        return (int) (this.id-o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cookingTimeInMin=" + cookingTimeInMin +
                ", productList=" + productList +
                ", orders=" + orders +
                '}';
    }

    public void addOrder(Order order) {
        addOrder(order,false);
    }
    public void addOrder(Order order, boolean setFromAnotherSide) {
        this.getOrders().add(order);
        if(setFromAnotherSide) {
            return;
        }
        order.addPizza(this,true);
    }
}
