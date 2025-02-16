package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.cart.CartItem;
import com.example.kkubeurakko.domain.menu.menuenum.MenuLabel;
import com.example.kkubeurakko.domain.menuOption.MenuOption;
import com.example.kkubeurakko.domain.order.OrderItem;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "description", length = 100)
    private String description;

    @NotNull
    @Column(name = "image_url", unique = true, length = 1000)
    private String imageUrl;

    @ElementCollection(targetClass = MenuLabel.class)
    @CollectionTable(name = "menu_label", joinColumns = @JoinColumn(name = "menu_id"))
    @Enumerated(EnumType.STRING)
    private Set<MenuLabel> labels = new HashSet<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuOption> options = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "menu")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "menu")
    private List<CartItem> cartItems = new ArrayList<>();

    //메뉴에 달린 모든 리뷰 조회
    public List<Review> getReviews() {
        return orderItems.stream()
                .map(OrderItem::getOrder)
                .filter(order -> order.getReview() != null)
                .map(Order::getReview)
                .collect(Collectors.toList());
    }

    public Menu(Long id, String name, BigDecimal price, String description, String imageUrl, LocalDateTime createDateTime, LocalDateTime modifiedDateTime){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createDateTime = createDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }
}
