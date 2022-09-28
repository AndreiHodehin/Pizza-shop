package proj.domain.DTO;

import proj.domain.Pizza;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class ProductPojo implements Comparable<ProductPojo>{
    private long id;
    private String prodName;
    private double minAmount;
    private String unit;
    private double totalStock;
    private SortedSet<Long> pojoSet;

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

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
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

    public SortedSet<Long> getPojoSet() {
        return pojoSet;
    }

    public void setPojoSet(Set<Long> pojoSet) {
        this.pojoSet = new TreeSet<>();
        this.pojoSet.addAll(pojoSet);
    }

    @Override
    public String toString() {
        return "ProductPojo{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", minAmount=" + minAmount +
                ", unit='" + unit + '\'' +
                ", totalStock=" + totalStock +
                ", pojoSet=" + pojoSet +
                '}';
    }

    @Override
    public int compareTo(ProductPojo o) {
        return (int) (this.id-o.id);
    }
}
