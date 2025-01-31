package com.project.ShowTimeBooking.Entities;

import com.project.ShowTimeBooking.Enums.City;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.util.Objects;
@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false)
    String plotNo;
    @Column(nullable = false)
    String locality;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    City city;
    @Column(nullable = false)
    Integer pinCode;
    @Column(nullable = false, unique = true)
    URL locationUrl;
    @OneToOne //parent with respect to the theatre #bidirectional mapping
    Theatre theatre;

    @Override
    public int hashCode() {
        return Objects.hash(id, code, plotNo, locality, city, pinCode, locationUrl);
    }
}
