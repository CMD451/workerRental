package indie.outsource.model;


import indie.outsource.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class Rent extends AbstractEntity {
    LocalDateTime startDate;

    LocalDateTime endDate;

    Worker worker;

    User user;
}
