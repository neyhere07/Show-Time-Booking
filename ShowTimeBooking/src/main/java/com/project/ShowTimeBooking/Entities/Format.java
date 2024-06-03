package com.project.ShowTimeBooking.Entities;

import com.project.ShowTimeBooking.Enums.FormatEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Entity
@Table
@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    FormatEnum name;
    @ManyToMany(mappedBy = "formatList")
    List<Movie> movieList;
}
