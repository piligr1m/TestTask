package org.martynov.spring.dao;

import org.martynov.spring.models.Car;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException
    {
        Car car = new Car();
        car.setColor(resultSet.getString("Color"));
        car.setHorsepower(resultSet.getInt("Horsepower"));
        car.setQuantity(resultSet.getInt("Quantity"));
        return car;
    }
}
