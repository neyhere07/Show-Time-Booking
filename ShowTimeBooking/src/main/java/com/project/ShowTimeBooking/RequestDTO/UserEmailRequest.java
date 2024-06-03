package com.project.ShowTimeBooking.RequestDTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEmailRequest {
    String Email;
}
