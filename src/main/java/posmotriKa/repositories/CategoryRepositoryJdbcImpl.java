package posmotriKa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import posmotriKa.models.Category;

import java.sql.PreparedStatement;
import java.util.Optional;


@Repository
public class CategoryRepositoryJdbcImpl implements CategoryRepository {

    private static final String SQL_SELECT_BY_NAME =
            "select * from posmotrika.category where categoryName = ?";
    private static final String SQL_INSERT =
            "insert into posmotrika.category (categoryName) values (?)";
    private final String SQL_SELECT_BY_ID =
            "select * from posmotrika.category where id = ?";
    private final String SQL_UPDATE =
            "update posmotrika.category set categoryName = ? where id = ?";
    private final String SQL_DELETE =
            "delete from posmotrika.category where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Category> categoryRowMapper = (row, rowNumber) ->
            Category.builder()
                    .id(row.getLong("id"))
                    .categoryName(row.getString("categoryName"))
                    .build();

    @Override
    public Optional<Category> update(Category category) {
        try {
            jdbcTemplate.update(SQL_UPDATE, category.getCategoryName(), category.getId());
            return Optional.of(category);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> save(Category category) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection
                        .prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, category.getCategoryName());

                return statement;
            }, keyHolder);

            category.setId((Long) keyHolder.getKeys().get("id"));
            return Optional.of(category);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> findById(Long id) {
        try {
            Category category = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, categoryRowMapper);
            return Optional.ofNullable(category);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> findByName(String categoryName) {
        try {
            Category category = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{categoryName}, categoryRowMapper);
            return Optional.ofNullable(category);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> delete(Long id) {
        return Optional.empty();
    }
}
