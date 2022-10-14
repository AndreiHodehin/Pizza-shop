package proj.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_pizza",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> orderedPizza = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Pizza> getOrderedPizza() {
        return orderedPizza;
    }

    public void setOrderedPizza(List<Pizza> orderedPizza) {
        this.orderedPizza = orderedPizza;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
       setUser(user,false);
    }
    public void setUser(User user, boolean setFromAnotherSide){
        this.user = user;
        if(setFromAnotherSide){
            return;
        }
        user.addOrder(this,true);
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
    public void addPizza(Pizza pizza){
        addPizza(pizza,false);
    }
    public void addPizza(Pizza pizza,boolean setFromAnotherSide){
        this.getOrderedPizza().add(pizza);
        if(setFromAnotherSide){
            return;
        }
        pizza.addOrder(this,true);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderedPizza=" + orderedPizza +
                ", user=" + user +
                ", createdTime=" + createdTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
