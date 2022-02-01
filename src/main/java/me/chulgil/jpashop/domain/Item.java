package me.chulgil.jpashop.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.chulgil.jpashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * 재고 추가
     * @param quantity 재고수량
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    @SneakyThrows
    public void deleteStock(int quantity) {
        this.stockQuantity -= quantity;
        if (this.stockQuantity < 0) {
            this.stockQuantity = 0;
            throw new NotEnoughStockException("need more stock");
        }
    }

}
