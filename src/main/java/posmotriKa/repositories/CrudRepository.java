package posmotriKa.repositories;

import java.util.Optional;

interface CrudRepository<T, ID> {

    Optional<T> update(T model);

    Optional<T> save(T model);

    Optional<T> findById(ID id);

    Optional<T> findByName(String name);

    Optional<T> delete(ID id);
}
