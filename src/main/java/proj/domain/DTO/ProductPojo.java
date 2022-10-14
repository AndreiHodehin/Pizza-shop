package proj.domain.DTO;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class ProductPojo implements Comparable<ProductPojo>{
    private long id;
    private String prodName;
    private double amount;
    private String unit;
    private SortedSet<Long> pizzaList;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public SortedSet<Long> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(Set<Long> pizzaList) {
        this.pizzaList = new TreeSet<>();
        this.pizzaList.addAll(pizzaList);
    }

    @Override
    public String toString() {
        return "ProductPojo{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", pizzaList=" + pizzaList +
                '}';
    }

    @Override
    public int compareTo(ProductPojo o) {
        return (int) (this.id-o.id);
    }
}
