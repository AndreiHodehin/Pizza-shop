package proj.domain;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Product implements Comparable<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String prodName;
    private double amountInUnit;
    private String unit;
    private double totalStock;
    @ManyToMany(mappedBy = "productList")
    @SortNatural
    private SortedSet<Pizza> pizzaList = new TreeSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getAmountInUnit() {
        return amountInUnit;
    }

    public void setAmountInUnit(double amountInUnit) {
        this.amountInUnit = amountInUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }

    public SortedSet<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(SortedSet<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public void addPizza(Pizza pizza){addPizza(pizza,false);}
    public void addPizza(Pizza pizza,boolean otherSideHasBeenSet) {
        this.getPizzaList().add(pizza);
        if(otherSideHasBeenSet) {
            return;
        }
        pizza.addProduct(this,true);
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", amountInUnit=" + amountInUnit +
                ", unit='" + unit + '\'' +
                ", totalStock=" + totalStock +
                ", pizzaList=" + pizzaList +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return (int) (this.id-o.id);
    }
}
