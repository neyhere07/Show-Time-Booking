package com.project.ShowTimeBooking.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmMakerResponse {
    String code;
    String name;
    List<ProfessionResponse> professionResponseList;
    LocalDate dateOfBirth;
    String birthPlace;
}
