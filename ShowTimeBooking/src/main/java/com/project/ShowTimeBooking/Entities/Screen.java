package com.project.ShowTimeBooking.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"theatre", "screenSeatList", "showList"})
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String screenNumber;
    @Column(nullable = false)
    int noOfSeats;
    @ManyToOne
    Theatre theatre;
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL) //parent with respect to screenSeat list bidirectional mapping
    List<ScreenSeat> screenSeatList;
    @OneToMany (mappedBy = "screen", cascade = CascadeType.ALL) //parent with respect to Show List bidirectional mapping
    List<Show>showList;
}
