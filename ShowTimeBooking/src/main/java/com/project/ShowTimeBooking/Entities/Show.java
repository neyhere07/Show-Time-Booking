package com.project.ShowTimeBooking.Entities;

import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "shows")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"movie", "showSeatList", "ticketList"})
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(name = "show_date", nullable = false)
    LocalDate date;
    @Column(nullable = false)
    LocalDateTime startTime;
    @Column(nullable = false)
    LocalDateTime endTime;
    @Column(nullable = false)
    LocalTime ticketCancellationTimeLimit;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    LanguageEnum languageEnum;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    FormatEnum formatEnum;
    @ManyToOne //here show is the child with respect to the movie
    Movie movie;
    @ManyToOne
    Screen screen;
    @OneToMany (mappedBy = "show", cascade = CascadeType.ALL) //parent with respect to the show seat
    List<ShowSeat>showSeatList;
    @OneToMany (mappedBy = "show", cascade = CascadeType.ALL)
    List<Ticket>ticketList;
}
