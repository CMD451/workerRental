package indie.outsource.WorkerRental.model;

import indie.outsource.WorkerRental.model.user.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rent extends AbstractEntity {
    public Rent(LocalDateTime startDate, Worker worker, User user) {
        this.startDate = startDate;
        this.worker = worker;
        this.user = user;
    }

    //https://www.baeldung.com/javax-validation-method-constraints
    @NotNull
    LocalDateTime startDate;

    LocalDateTime endDate;

    Worker worker;

    User user;
}
