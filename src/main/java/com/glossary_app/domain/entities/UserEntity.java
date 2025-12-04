package com.glossary_app.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserEntity {

    @Id
    @Column("user_id")
    private UUID userId;

    @Column("user_name")
    @NonNull
    private String userName;

    @Column("total_words")
    private Integer totalTerms;

    @Column("created_date")
    private Instant createdDate;

    @Column("deleted_date")
    private Instant deletedDate;
}