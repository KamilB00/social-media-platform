package com.socialmediaplatform.infrastructure.repository;

import javax.persistence.*;

@Entity
public class LikeEntity {

    @Id
    @Column(name = "liking_user",unique=true,columnDefinition="VARCHAR(64)")
    private String likingUser;
}
