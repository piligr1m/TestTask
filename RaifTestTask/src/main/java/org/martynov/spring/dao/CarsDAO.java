package org.martynov.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import org.martynov.spring.models.*;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class CarsDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CarsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Car> getCars(String color, int horsepower, String operation)
    {
        switch (operation)
        {
            case "equal":
            {
                return jdbcTemplate.query("SELECT * FROM cars WHERE color = ? AND horsepower = ?",
                        new Object[]{color, horsepower}, new BeanPropertyRowMapper<>(Car.class));
            }
            case "lessThan":
            {
                return jdbcTemplate.query("SELECT * FROM cars WHERE color = ? AND horsepower < ?",
                        new Object[]{color, horsepower}, new BeanPropertyRowMapper<>(Car.class));
            }
            case "moreThan":
            {
                return jdbcTemplate.query("SELECT * FROM cars WHERE color = ? AND horsepower > ?",
                        new Object[]{color, horsepower}, new BeanPropertyRowMapper<>(Car.class));
            }
            default:
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
    private boolean checkExistence(String color, int horsepower)
    {
        return jdbcTemplate.query("SELECT color, horsepower, quantity FROM cars WHERE color = ? AND horsepower = ?", new
                Object[]{color, horsepower}, new BeanPropertyRowMapper<>(Car.class)).stream().findAny().isPresent();
    }
    public int incomeCars(String color, int horsepower, int quantity)
    {
            boolean isExist = checkExistence(color, horsepower);
            if (isExist)
            {
                jdbcTemplate.update("UPDATE cars SET quantity = ? WHERE color = ? AND horsepower = ?",
                        new Object[]{quantity, color, horsepower});
            }
            if (!isExist)
            {
                jdbcTemplate.update("INSERT INTO cars (color, horsepower, quantity) VALUES (?, ?, ?)",
                        new Object[]{color, horsepower, quantity});
            }
        return quantity*horsepower*Const.BASE_RATE.getNumber();
    }
    public int outcomeCars(String color, int horsepower, int quantity)
    {
        boolean isExist = checkExistence(color, horsepower);
        if (!isExist)
        {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        List<Car> car1 = jdbcTemplate.query("SELECT * FROM cars WHERE color = ? AND horsepower = ?",
                new Object[]{color, horsepower}, new BeanPropertyRowMapper<>(Car.class));
        Car car = car1.get(0);
        if (car.getQuantity() < quantity)
        {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        if (car.getQuantity() > quantity)
        {
            jdbcTemplate.update("UPDATE cars SET quantity = ? WHERE color = ? AND horsepower = ?",
                    new Object[]{(car.getQuantity() - quantity), color, horsepower});
        }
        if (car.getQuantity() == quantity)
        {
            jdbcTemplate.update("DELETE FROM cars WHERE color = ? AND horsepower = ?",
                    new Object[]{color, horsepower});
        }
        return quantity*horsepower*Const.BASE_RATE.getNumber();
    }


}
