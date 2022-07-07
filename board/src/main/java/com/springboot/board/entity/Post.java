package com.springboot.board.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;



@Entity
@Table(name="post")
@Getter
@Setter
public class Post {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Size(min=2, max=45, message = "The length of title must be at least 2 characters and no more than 45 characters.")
    @Column(name="title")
    private String title;

    @NotNull
    @Size(min=2, message = "The length of content must be at least 2 characters.")
    @Column(name="content", columnDefinition = "TEXT")
    private String content;

    @NotNull
    @Size(min=2, message = "The length of writer must be at least 2 characters.")
    @Column(name="writer")
    private String writer;

    @Column(name="hits", nullable = false)
    private int hits;

    @Column(name = "date_created")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
