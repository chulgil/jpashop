package me.chulgil.jpashop.repository;

import lombok.Getter;
import lombok.Setter;
import me.chulgil.jpashop.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
