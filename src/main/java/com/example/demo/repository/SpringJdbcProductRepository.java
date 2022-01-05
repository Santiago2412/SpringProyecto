package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductId;
import com.example.demo.domain.ProductName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class SpringJdbcProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> rowMapper = (resultSet, rowNum) -> {
        ProductId productId = ProductId.fromString(
                resultSet.getString("id_number")
        );
        ProductName productName = new ProductName(resultSet.getString("name"));

        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();


        return new Product(
                productId,
                productName,
                birthday

        );
    };


    @Override
    public List<Product> list() {
        String sqlQuery = "select * from product ";
        return jdbcTemplate.query(sqlQuery, rowMapper);
    }

    @Override
    public Product findOne(ProductId id) {
        String sqlQuery = "select * from product where id_number = ?";

        return jdbcTemplate.queryForObject(sqlQuery, rowMapper, id.toString());
    }

    @Override
    public void create(Product product) {
        String sqlQuery = "insert into product (id_number, name, birthday) values(?, ?, ?)";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, product.getId().toString());
            ps.setString(2, product.getName().toString());
            ps.setDate(3, Date.valueOf(product.getBirthday()));

        });
    }

    @Override
    public void update(ProductId id, Product product) {
        String sqlQuery = "update product set birthday = ?, name = ? where id_number = ?";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setDate(1, Date.valueOf(product.getBirthday()));
            ps.setString(2, product.getName().toString());
            ps.setString(3, id.toString());
        });
    }

    @Override
    public void delete(ProductId id) {
        String sqlQuery = "delete from product where id_number = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
