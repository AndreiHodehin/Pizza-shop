package proj.domain.DTO;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class PizzaPojo implements Comparable<PizzaPojo> {
    private long id;
    private String name;
    private long cookingTimeInMin;
    private SortedSet<ProductPojo> productList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortedSet<ProductPojo> getProductList() {
        return productList;
    }

    public long getCookingTimeInMin() {
        return cookingTimeInMin;
    }

    public void setCookingTimeInMin(long cookingTimeInMin) {
        this.cookingTimeInMin = cookingTimeInMin;
    }

    public void setProductList(SortedSet<ProductPojo> productList) {
        this.productList = productList;
    }

    public void setProductList(Set<ProductPojo> products) {
        productList = new TreeSet<>();
        productList.addAll(products);
    }


    @Override
    public String toString() {
        return "PizzaPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cookingTimeInMin=" + cookingTimeInMin +
                ", productList=" + productList +
                '}';
    }

    @Override
    public int compareTo(PizzaPojo o) {
        return (int) (this.id-o.id);
    }
}
