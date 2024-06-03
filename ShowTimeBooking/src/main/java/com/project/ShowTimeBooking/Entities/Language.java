package com.project.ShowTimeBooking.Entities;

import com.project.ShowTimeBooking.Enums.LanguageEnum;
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
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    LanguageEnum name;
    @ManyToMany(mappedBy = "languagesReleasedIn")
    List<Movie> movieList;
}
