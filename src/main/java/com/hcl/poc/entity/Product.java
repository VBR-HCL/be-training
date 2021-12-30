package com.hcl.poc.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "poc_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
