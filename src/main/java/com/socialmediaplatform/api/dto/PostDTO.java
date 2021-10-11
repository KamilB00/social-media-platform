package com.socialmediaplatform.api.dto;

import com.socialmediaplatform.domain.publisher.PublisherService;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PostDTO implements PublisherService.Command.CreatePost {
private boolean isEdited;
private String content;
private Date publicationDate;
}
