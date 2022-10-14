package proj.domain.DTO;

public class ProductInStockPojo implements Comparable<ProductInStockPojo>{
    private long id;
    private String nameOfProd;
    private String unit;
    private double totalStock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfProd() {
        return nameOfProd;
    }

    public void setNameOfProd(String nameOfProd) {
        this.nameOfProd = nameOfProd;
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

    @Override
    public String toString() {
        return "StockOfProductPojo{" +
                "id=" + id +
                ", nameOfProd='" + nameOfProd + '\'' +
                ", unit='" + unit + '\'' +
                ", totalStock=" + totalStock +
                '}';
    }

    @Override
    public int compareTo(ProductInStockPojo o) {
        return (int) (this.id-o.id);
    }

}
