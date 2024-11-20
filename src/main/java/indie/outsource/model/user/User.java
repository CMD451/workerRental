package indie.outsource.model.user;


import indie.outsource.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User extends AbstractEntity {

    private String login;
    private String password;
    private boolean active;


}
