package posmotriKa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import posmotriKa.models.UserInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserInfoRepositoryJpaImpl implements UserInfoRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = ClassNotFoundException.class)
    public Optional<UserInfo> update(UserInfo model) {
        UserInfo userInfo = entityManager.merge(model);
        return Optional.ofNullable(userInfo);
    }


    @Override
    @Transactional
    public Optional<UserInfo> save(UserInfo model) {
        entityManager.persist(model);
        return Optional.ofNullable(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserInfo> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserInfo.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserInfo> findByName(String name) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UserInfo> delete(Long id) {
        return Optional.empty();
    }
}
