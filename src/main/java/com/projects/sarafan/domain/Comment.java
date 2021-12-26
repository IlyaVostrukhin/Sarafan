package com.projects.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
@EqualsAndHashCode(of = { "id" })
public class Comment {
    @Id
    @GeneratedValue
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.Id.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id")
    @JsonView(Views.IdName.class)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonView(Views.IdName.class)
    private User author;
}
