package me.chulgil.jpashop.service;

import me.chulgil.jpashop.domain.*;
import me.chulgil.jpashop.exception.NotEnoughStockException;
import me.chulgil.jpashop.repository.OrderRepository;
import me.chulgil.jpashop.repository.OrderSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cglee
 * 통합 테스트 : 주문 서비스
 */
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
        Member member = createMember();
        Book book = createBook("JPA상점", 1000, 10);

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
        Member member = createMember();
        Item item = createBook("JPA상점", 1000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order order = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCLE", OrderStatus.CANCEL, order.getStatus());
        assertEquals("취소 상품은 재고가 롤백", 10, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA상점", 1000, 10);

        int orderCount = 11;

        //when
        Long order = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문검색() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA상점", 1000, 10);
        Item item2 = createBook("JPA상점", 1000, 10);

        Long orderId1 = orderService.order(member.getId(), item.getId(), 1);
        Long orderId2 = orderService.order(member.getId(), item2.getId(), 1);
        orderService.cancelOrder(orderId2);

        //when
        OrderSearch search = new OrderSearch();
        search.setMemberName("회원1");
        search.setOrderStatus(OrderStatus.ORDER);
        List<Order> orders = orderService.findOrders(search);

        //then
        assertEquals("주문검색 결과가 2건", 1, orders.size());
    }


    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강변로", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }


    
}