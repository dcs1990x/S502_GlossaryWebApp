package com.glossary_app.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("terms")
public class TermEntity {

    @Id
    @Column("term_id")
    private Long termId;

    @Column("term_text")
    private String term;
}