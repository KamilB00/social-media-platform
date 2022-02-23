package com.socialmediaplatform.infrastructure.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
@Getter
@Setter
public abstract class BaseTuple {
    private String uuid = UUID.randomUUID().toString();
}

