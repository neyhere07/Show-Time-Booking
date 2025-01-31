package com.project.ShowTimeBooking.Entities;

import com.project.ShowTimeBooking.Enums.GenreEnum;
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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    GenreEnum name;
    @ManyToMany(mappedBy = "genreList")
    List<Movie> movieList;
}
