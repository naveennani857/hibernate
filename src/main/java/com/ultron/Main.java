package com.ultron;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        laptop.setLaptopName("Dell");
        laptop.setLaptopModel("Inspiron 15");

        Employee employee = new Employee();
        employee.setEmpId(5);
        employee.setName("Thor");
        employee.setSalary(110000.00);
        employee.setDepartment("Love and Thunder");
        employee.setLaptop(laptop);

//        Configuration cfg = new Configuration();
//        cfg.addAnnotatedClass(org.ultron.Employee.class);
//        cfg.configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //We can write the above 4 lines in a  single line.
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .configure()
                .buildSessionFactory();    // rich resource, has to close or present in try with resource

        Session session = sessionFactory.openSession();

        insertValues(session,employee);
        //updateData(session,employee);
        //deleteData(session);
        Employee e1 =  getByPrimaryKey(session,5);

        if(e1 !=null) System.out.println(e1);
        else System.err.println("Primary key not present");

        session.close();
        sessionFactory.close();
    }

    private static void insertValues(Session session, Employee employee){

        Transaction tr = session.beginTransaction(); //is required for Inserting the data
        session.persist(employee);     //save method is deprecated
        tr.commit();

        System.out.println(employee);
    }
    private static Employee getByPrimaryKey(Session session,int primaryKey){
        return session.get(Employee.class,primaryKey);
    }

    private static void updateData(Session session,Employee employee){
        Transaction transaction = session.beginTransaction();

        session.merge(employee);

        transaction.commit();
    }

    private static void deleteData(Session session) {
        Transaction transaction = session.beginTransaction();
        session.remove(getByPrimaryKey(session,5));
        transaction.commit();
    }
}