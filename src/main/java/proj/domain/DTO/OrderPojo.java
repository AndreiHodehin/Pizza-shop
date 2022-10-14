package proj.domain.DTO;

import proj.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderPojo {
    private long id;
    private List<PizzaPojo> orderedPizza = new ArrayList<>();
    private User user;
    private LocalDateTime createdTime;
    private boolean prepared;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PizzaPojo> getOrderedPizza() {
        return orderedPizza;
    }

    public void setOrderedPizza(List<PizzaPojo> orderedPizza) {
        this.orderedPizza = orderedPizza;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    @Override
    public String toString() {
        return "OrderPojo{" +
                "id=" + id +
                ", orderedPizza=" + orderedPizza +
                ", user=" + user +
                ", createdTime=" + createdTime +
                ", prepared=" + prepared +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPojo)) return false;
        OrderPojo pojo = (OrderPojo) o;
        return id == pojo.id && Objects.equals(orderedPizza, pojo.orderedPizza) && Objects.equals(user, pojo.user) && Objects.equals(createdTime, pojo.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderedPizza, user, createdTime);
    }
}
