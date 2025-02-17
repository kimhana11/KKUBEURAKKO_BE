package com.example.kkubeurakko.domain.review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "image_url", length = 1000)
    private String imageUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

}
