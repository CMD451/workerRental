package indie.outsource.repositories;


import indie.outsource.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends BaseRepository<User, UUID> {

    Optional<User> findByLogin(String login);
    List<User> findByLoginContainsIgnoreCase(String login);
}
