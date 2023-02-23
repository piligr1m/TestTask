CREATE TABLE cars
(
    id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    color varchar(20),
    horsepower int,
    quantity int
);