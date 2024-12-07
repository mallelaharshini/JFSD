package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                   .addAnnotatedClass(Vehicle.class)
                                                   .addAnnotatedClass(Car.class)
                                                   .addAnnotatedClass(Truck.class)
                                                   .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();

        try {
            // Create Vehicle objects
            Vehicle vehicle1 = new Vehicle("Vehicle1", "Type1", 100, "Red");
            Car car1 = new Car("Car1", "Sedan", 150, "Blue", 4);
            Truck truck1 = new Truck("Truck1", "Cargo", 80, "Green", 2000);

            // Start a transaction
            session.beginTransaction();

            // Save the objects
            session.save(vehicle1);
            session.save(car1);
            session.save(truck1);

            // Commit the transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
