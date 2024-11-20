package indie.outsource.dtoMappers;


import indie.outsource.DTO.user.CreateUserDTO;
import indie.outsource.DTO.user.USERTYPE;
import indie.outsource.DTO.user.UserDTO;
import indie.outsource.model.user.Admin;
import indie.outsource.model.user.Client;
import indie.outsource.model.user.Manager;
import indie.outsource.model.user.User;

public final class UserMapper {
    public static UserDTO getUserDTO(User user){
        System.out.println(user.getClass().getSimpleName());
        return new UserDTO(user.getLogin(), user.isActive(), user.getId(), USERTYPE.getByClassname(user.getClass().getSimpleName()));
    }
    public static User getUser(CreateUserDTO createUserDTO){
        User user = new User();
        if(createUserDTO.getType() == USERTYPE.CLIENT){
            user = new Client();
        }
        if(createUserDTO.getType() == USERTYPE.MANAGER){
            user = new Manager();
        }
        if(createUserDTO.getType() == USERTYPE.ADMIN){
            user = new Admin();
        }
        user.setLogin(createUserDTO.getLogin());
        user.setPassword(createUserDTO.getPassword());
        return user;
    }

}
