package posmotriKa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import posmotriKa.models.Video;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class VideoRepositoryJpaImpl implements VideoRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Video> update(Video model) {
        try {
            Video video = entityManager.merge(model);
            return Optional.ofNullable(video);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> save(Video model) {
        try {
            entityManager.persist(model);
            return Optional.ofNullable(model);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(Video.class, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> findByName(String name) {
        try {
            Video video = entityManager.createQuery("select v from Video v where v.videoName = :name", Video.class).setParameter("name", name).getSingleResult();
            return Optional.ofNullable(video);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> delete(Long id) {
        try {
            Video video = findById(id).get();
            entityManager
                    .remove(video);
            return Optional.ofNullable(video);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
