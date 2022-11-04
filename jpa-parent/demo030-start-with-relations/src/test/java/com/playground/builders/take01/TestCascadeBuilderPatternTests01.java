package com.playground.builders.take01;


import org.junit.jupiter.api.Test;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.playground.builders.take01.Order.OrderBuilder.createOrder;


public class TestCascadeBuilderPatternTests01 {

    @Test
    void createABuilderForOrder() {
        Order pindakaas1 = createOrder()
                .withDate(LocalDate.now())
                .withOrderItems()
                .newItem(
                        OrderItem.OrderItemBuilder.addItem()
                                .withProduct(new Product("Pindakaas"))
                                .withQuantity(2)
                                .build()
                ).addOrderItemsToOrder().build();

        Order pindakaas2 = createOrder()
                .withDate(LocalDate.now())
                .withOrderItems()
                .newItem().withProduct(new Product("Pindakaas")).withQuantity(2).addToList()
                .addOrderItemsToOrder().build();
    }
}

class Order {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private LocalDate orderDate;

    private List<OrderItem> orderItems;

    static class OrderBuilder {

        private LocalDate orderDate;
        private OrderItemListBuilder orderItemListBuilder;

        public static OrderBuilder createOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public OrderItemListBuilder withOrderItems() {
            this.orderItemListBuilder = new OrderItemListBuilder(this);
            return this.orderItemListBuilder;
        }

        public Order build() {
            Order order = new Order();
            order.orderDate = orderDate;
            order.orderItems = orderItemListBuilder.items;
            return order;
        }
    }

    static class OrderItemListBuilder {
        private Order.OrderBuilder orderBuilder;

        private List<OrderItem> items = new ArrayList<OrderItem>();

        OrderItemListBuilder(Order.OrderBuilder orderBuilder) {
            this.orderBuilder = orderBuilder;
        }

        public OrderItemListBuilder newItem(OrderItem orderItem) {
            items.add(orderItem);
            return this;
        }

        public OrderItem.OrderItemBuilder newItem() {
            return new OrderItem.OrderItemBuilder(this);
        }

        public Order.OrderBuilder addOrderItemsToOrder() {
            return orderBuilder;
        }

        public void addItem(OrderItem orderItem) {
            this.items.add(orderItem);
        }
    }
}

class OrderItem {
    private final Product product;
    private final int quantity;
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static class OrderItemBuilder {
        private Order.OrderItemListBuilder orderItemListBuilder;
        private Product product;
        private int quantity;

        public OrderItemBuilder(Order.OrderItemListBuilder orderItemListBuilder) {
            this.orderItemListBuilder = orderItemListBuilder;
        }

        public OrderItemBuilder() {

        }

        public static OrderItemBuilder addItem() {
            return new OrderItemBuilder();
        }

        public OrderItemBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public OrderItemBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            OrderItem orderItem = new OrderItem(product, quantity);
            return orderItem;
        }

        public Order.OrderItemListBuilder addToList() {
            OrderItem orderItem = new OrderItem(product, quantity);
            this.orderItemListBuilder.addItem(orderItem);
            return this.orderItemListBuilder;
        }
    }
}

class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }
}
