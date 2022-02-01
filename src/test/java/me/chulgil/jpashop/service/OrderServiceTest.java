package me.chulgil.jpashop.service;

import me.chulgil.jpashop.domain.*;
import me.chulgil.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강변로", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("JPA상점");
        book.setPrice(1000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getStatus());
        assertEquals("주문상품수가 일치", 1, order.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량", 1000 * orderCount, order.getTotalPrice());
        assertEquals("주문한 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());

    }
    
    @Test
    public void 주문수량() throws Exception {
        //given
        
        //when
        
        //then
    }
    
    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        
        //when
        
        //then
    }
    
    
}