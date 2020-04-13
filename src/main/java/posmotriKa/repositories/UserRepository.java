package posmotriKa.repositories;

import posmotriKa.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
}
