package com.socialmediaplatform.domain.user;

import lombok.*;


@Getter
@Builder
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    private String username;
}


