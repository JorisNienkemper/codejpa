package com.playground.builders.take02;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.playground.builders.take02.Order.OrderBuilder.newOrder;
import static com.playground.builders.take02.OrderItem.OrderItemBuilder.newOrderItem;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCascadeBuilderPatternTests02 {

    // 00 Create the following test
    @Test
    void createAnOrderWithOnlyAnOrderDate() {

        Order o = newOrder()
                .withOrderDate(now())
                .build();
        assertThat(o.getOrderDate()).isBeforeOrEqualTo(now());
    }

    //10 examine the following test
    @Test
    void createanOrderWithAnOrderDateAndOnlyOneOrderItem() {
        Order o = newOrder()
                .withOrderDate(now())
                .withOrderItem(newOrderItem()
                        .withProduct("pindakaas")
                        .withQuantity(3)
                        .build())
                .build();

        //20 create a new instance of the type SoftAssertions
        //21 use this instance to individually assertThat the
        // localdate is before or equal to now()
        // that the orderdetail product is equal to "pindakaas"
        // create the necessary getters to achieve this
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(o.getOrderDate()).isAfterOrEqualTo(now());
        softAssertions.assertThat(o.getOrderItem().getProduct()).isEqualTo("pindakaas");
        softAssertions.assertAll();
    }

    //30 An Order with only one OrderItem is rather lame
    //   We want an order with a List of OrderItems
    //   Keep the functionality to support one OrderItem we will remove it later
    //   Add an attribute of type List<OrderItem> orderItems to Order
    //   Add a new builder class, the OrdeItemsListBuilder inside Order that takes care of building the list
    @Test
    void createAnOrderWithAnOrderDateAndAndAListOfOrderItems() {
        Order o = newOrder()
                .withOrderDate(now())
                .withOrderItems()
                .newItem(newOrderItem()
                        .withProduct("pinda")
                        .withQuantity(3)
                        .build())
                .newItem(newOrderItem()
                        .withProduct("kaas")
                        .withQuantity(3)
                        .build())
                .addItemsToOrder()
                .build();

        //20 create a new instance of the type SoftAssertions
        //21 use this instance to individually assertThat the
        // localdate is before or equal to now()
        // that the orderdetail product is equal to "pindakaas"
        // create the necessary getters to achieve this
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(o.getOrderDate()).isAfterOrEqualTo(now());
        softAssertions.assertThat(o.getOrderItems().size()).isEqualTo(2);
        softAssertions.assertAll();
    }

    //40 Implement logic in the newItem method to prohibit duplicate orderItems of the same product
    //   When a duplicate entry is added then the quantity of the orderItem already in the list should be modified 
    @Test
    void createASmarterOrderItemsListBuilder() {
        Order o = newOrder()
                .withOrderDate(now())
                .withOrderItems()
                .newItem(newOrderItem()
                        .withProduct("pindakaas")
                        .withQuantity(3)
                        .build())
                .newItem(newOrderItem()
                        .withProduct("pindakaas")
                        .withQuantity(3)
                        .build())
                .addItemsToOrder()
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(o.getOrderDate()).isAfterOrEqualTo(now());
        softAssertions.assertThat(o.getOrderItems().size()).isEqualTo(1);
        OrderItem orderItem = o.getOrderItems().get(0);
        softAssertions.assertThat(orderItem.getProduct()).isEqualTo("pindakaas");
        softAssertions.assertThat(orderItem.getQuantity()).isEqualTo(6);
        softAssertions.assertAll();
    }
}

// 01) First build the Order class with only the property private LocalDate orderDate;
// 02)The @Id property and @Version property are automatically taken care of by JPA
class Order {

    private final LocalDate orderDate;

    //11 add an OrderItem attribute
    private final OrderItem orderItem;

    private final List<OrderItem> orderItems;

    public Order(LocalDate orderDate, OrderItem orderItem, List<OrderItem> orderItems) {
        this.orderDate = orderDate;
        this.orderItem = orderItem;
        this.orderItems = orderItems;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    //03 Create public static inner class OrderBuilder
    public static class OrderBuilder {
        //05 Add one attribute orderDate
        private LocalDate orderDate;

        //18 add attribute of type OrderItem
        private OrderItem orderItem;
        private List<OrderItem> orderItems;


        //04 create a method newOrder that returns an instance of OrderBuilder
        public static OrderBuilder newOrder() {
            return new OrderBuilder();
        }

        //06 Create a withOrderDate method expecting a parameter of type LocalDate
        //   used to set the attribute orderDate return a reference to the OrderBuilder
        public OrderBuilder withOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        //07 create a method build() to create order and return it
        //19 adjust the constructor to also take into account the orderItem parameter
        //35 include the list of orderItems in the Order, use a constructor parameter
        public Order build() {
            return new Order(orderDate, orderItem, orderItems);
        }

        //17 create a method withOrderItem expecting a paratmeter of type OrderItem
        //   assign the value of the parameter to a new attribute of the same type inside this builder
        public OrderBuilder withOrderItem(OrderItem orderItem) {
            this.orderItem = orderItem;
            return this;
        }

        //32 create a method withOrderItems that returns the OrdeItemsListBuilder
        //   add in the constructor of OrdeItemsListBuilder a reference to the current OrderBuilder
        //   we need this reference to go back to the OrderBuilder if we finished creating the list
        public OrderItemsListBuilder withOrderItems() {
            return new OrderItemsListBuilder(this);
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }
    }

    //31 create a static class OrdeItemsListBuilder
    public static class OrderItemsListBuilder {

        private final OrderBuilder orderBuilder;
        //31 It has an attribute of type List<OrderItem> orderItems initialize the List
        private final List<OrderItem> orderItems = new ArrayList<>();

        public OrderItemsListBuilder(OrderBuilder orderBuilder) {
            this.orderBuilder = orderBuilder;
        }

        //33 create a method newItem with param orderItem
        //   Add the reference to the list of orderItems and return
        //   reference to the OrderItemsListBuilder
        public OrderItemsListBuilder newItem(OrderItem orderItem) {
            String product = orderItem.getProduct();
            Optional<OrderItem> duplicateExistingOrderItem = orderItems
                    .stream()
                    .filter(item -> item.getProduct().equalsIgnoreCase(product))
                    .findAny();
            duplicateExistingOrderItem.ifPresentOrElse(item -> item.addQuantity(orderItem.getQuantity()), () -> this.orderItems.add(orderItem));
            return this;
        }

        //34 When the addItemsToOrder method is called adding orderItems to the list
        //  has finished. Return the List to the OrderBuilder via the setOrderItems method
        //  and return the reference to the orderBuilder
        public OrderBuilder addItemsToOrder() {
            this.orderBuilder.setOrderItems(orderItems);
            return orderBuilder;
        }
    }
}

//12 Create a class OrderItem add 2 fields
//   One of type String product and one of type int quantity
//   The @Id and @Version property are automatically set by JPA
class OrderItem {
    private final String product;
    private int quantity;

    public OrderItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    //13 Create a public static class inside OrderItem OrderItemBuilder
    //   it has the same attributes as OrderItem
    public static class OrderItemBuilder {
        private String product;
        private int quantity;

        //14 create 2 with<AttributeName> methodes that expect a parameter of attribute type
        //   Use the parameter to set the corresponding atttribute in the builder
        //   return a reference to the builder

        //15 create a static method newOrderItem() that returns an instance of OderItemBuilder
        public static OrderItemBuilder newOrderItem() {
            return new OrderItemBuilder();
        }

        public OrderItemBuilder withProduct(String product) {
            this.product = product;
            return this;
        }

        public OrderItemBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        //16 create a method build() that returns an instance of type OrderItem
        // populated with the values saved in the state of the builder
        public OrderItem build() {
            return new OrderItem(product, quantity);
        }
    }
}
