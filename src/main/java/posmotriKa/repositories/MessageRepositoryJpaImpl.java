package posmotriKa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import posmotriKa.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryJpaImpl implements MessageRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Message> update(Message model) {
        Message message = entityManager.merge(model);
        return Optional.ofNullable(message);
    }

    @Override
    @Transactional
    public Optional<Message> save(Message model) {
        entityManager.persist(model);
        return Optional.ofNullable(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Message> findByName(String name) {
        Message message = entityManager
                .createQuery("select m from Message m where m.pageId = :name and m.text = :login", Message.class)
                .setParameter("name", name)
                .setParameter("login", "SignIn")
                .getSingleResult();
        return Optional.ofNullable(message);
    }

    @Override
    @Transactional
    public Optional<Message> delete(Long id) {
        Message message = findById(id).get();
        entityManager
                .remove(message);
        return Optional.ofNullable(message);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Message> findAll() {
        List messages = entityManager.createQuery("select m from Message m")
                .getResultList();
        return messages;
    }
}
