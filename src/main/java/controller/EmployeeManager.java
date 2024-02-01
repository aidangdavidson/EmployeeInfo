package controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Employee;

import java.util.List;

/**
 * @author aidan - agdavidson
 * CIS175 - Spring 2024
 * Feb 1, 2024
 */

public class EmployeeManager {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("EmployeeInfo");

    public void insertEmployee(Employee employee) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public List<Employee> showAllEmployees() {
        EntityManager em = emfactory.createEntityManager();
        List<Employee> allEmployees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        em.close();
        return allEmployees;
    }

    public void deleteEmployee(Employee toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, toDelete.getId());
        if (employee != null) {
            em.remove(employee);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void updateEmployee(Employee toEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }
    
    public Employee searchForEmployeeById(int idToEdit) {
        EntityManager em = emfactory.createEntityManager();
        Employee found = em.find(Employee.class, idToEdit);
        em.close();
        return found;
    }

    public void cleanUp() {
        emfactory.close();
    }
}
