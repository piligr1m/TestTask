package org.martynov.spring.controller;

import org.martynov.spring.models.Car;
import org.martynov.spring.dao.CarsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MyController {
    private final CarsDAO carsDAO;
    @Autowired
    public MyController(CarsDAO carsDAO) {
        this.carsDAO = carsDAO;
    }
    @GetMapping()
    public String homepage()
    {
        return "cars/homepage";
    }
    @GetMapping("/api/cars")
    public String getCars(@RequestParam("color") String color, @RequestParam("operation") String operation,
                             @RequestParam("horsepower") int horsepower, Model model)
    {
        model.addAttribute("response", carsDAO.getCars(color, horsepower, operation));
        return "cars/getCars";
    }
    @PostMapping( value = "/api/cars/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String incomeCars(@RequestBody Car car, Model model)
    {
        model.addAttribute("response", carsDAO.incomeCars(car.getColor(), car.getHorsepower(), car.getQuantity()));
        return "cars/incomeCars";
    }
    @PostMapping( value = "/api/cars/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String outcomeCars(@RequestBody Car car, Model model)
    {
        model.addAttribute("response", carsDAO.outcomeCars(car.getColor(), car.getHorsepower(), car.getQuantity()));
        return "cars/outcomeCars";
    }
}
