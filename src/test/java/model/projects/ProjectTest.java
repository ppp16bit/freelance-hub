package model.projects;

import model.users.User;
import model.users.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
class ProjectTest {
    private Project project;
    private User customer;
    private User freelancer;

    @BeforeEach
    void setUp() {
        customer = new User();
        customer.setId(UUID.randomUUID());
        customer.setName("Pedro Henrique");
        customer.setEmail("ppp123@gmail.com");
        customer.setPassword("ppp123");
        customer.setMainRole(UserRole.CUSTOMER);

        freelancer= new User();
        freelancer.setId(UUID.randomUUID());
        freelancer.setName("Pedro Henrique");
        freelancer.setEmail("ppp321@gmail.com");
        freelancer.setPassword("ppp321");
        freelancer.setMainRole(UserRole.FREELANCER);

        project = new Project();
        project.setId(UUID.randomUUID());
        project.setTitle("Freelancer Manager");
        project.setDescription("Smooth Operatooor");
        project.setTerm(LocalDate.of(2025, 3, 17));
        project.setBudget(10000.00);
        project.setStatus(ProjectStatus.OPEN);
        project.setCustomer(customer);
    }

    @Test
    void testGetId() {
        assertNotNull(project.getId());
        System.out.println("id: " + project.getId());
    }

    @Test
    void testGetTitle() {
        assertEquals("Freelancer Manager", project.getTitle());
    }

    @Test
    void testGetDescription() {
        assertEquals("Smooth Operatooor", project.getDescription());
    }

    @Test
    void testGetTerm() {
        assertEquals(LocalDate.of(2025, 3, 17), project.getTerm());
    }

    @Test
    void testGetBudget() {
        assertEquals(10000.00, project.getBudget());
    }

    @Test
    void testGetStatus() {
        assertEquals(ProjectStatus.OPEN, project.getStatus());
    }

    @Test
    void testGetCustomer() {
        assertEquals(customer, project.getCustomer());
    }

    @Test
    void testSetId() {
        UUID newId = UUID.randomUUID();
        project.setId(newId);
        assertEquals(newId, project.getId());
        System.out.println("id: " + project.getId());
    }

    @Test
    void testSetTitle() {
        project.setTitle("New Title");
        assertEquals("New Title", project.getTitle());
        System.out.println("title: " + project.getTitle());
    }

    @Test
    void testSetDescription() {
        project.setDescription("New Description");
        assertEquals("New Description", project.getDescription());
        System.out.println("Description: " + project.getDescription());
    }

    @Test
    void testSetBudget() {
        project.setBudget(10500.00);
        assertEquals(10500.00, project.getBudget());
        System.out.println("Budget: " + project.getBudget());
    }

    @Test
    void testSetStatus() {
        project.setStatus(ProjectStatus.IN_PROGRESS);
        assertEquals(ProjectStatus.IN_PROGRESS, project.getStatus());
        System.out.println("status: " + project.getStatus());
    }

    @Test
    void testSetCustomer() {
        User newCustomer = new User();
        newCustomer.setId(UUID.randomUUID());
        newCustomer.setName("Pedro Henrique");
        newCustomer.setEmail("ppp123@gmail.com");
        newCustomer.setPassword("ppp123");
        newCustomer.setMainRole(UserRole.CUSTOMER);

        project.setCustomer(newCustomer);
        assertEquals(newCustomer, project.getCustomer());
    }

    @Test
    void testSetBudgetNegativeValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> project.setBudget(-100.00));
    }

    @Test
    void testSetCustomerNullThrowsExceptions() {
        assertThrows(NullPointerException.class, () -> project.setCustomer(null));
    }

    @Test
    void testSetFreelancer() {
        project.setFreelancer(freelancer);
        assertEquals(freelancer, project.getFreelancer());
    }

    @Test
    void testSetFreelancerNull() {
        project.setFreelancer(null);
        assertNull(project.getFreelancer());
    }

    @Test
    void testFreelancerMustBeFreelancerRole() {
        User invalidUser = new User();
        invalidUser.setId(UUID.randomUUID());
        invalidUser.setName("Invalid user");
        invalidUser.setEmail("user.invalid@invalid.com");
        invalidUser.setPassword("ppp123");
        invalidUser.setMainRole(UserRole.CUSTOMER);

        assertThrows(IllegalArgumentException.class, () -> project.setFreelancer(invalidUser));
    }

    @Test
    void testFreelancerCanbeAssignedToMultipleProjects() {
        Project project2 = new Project();
        project2.setId(UUID.randomUUID());
        project2.setTitle("API development");
        project2.setDescription("api");
        project2.setTerm(LocalDate.of(2025, 3, 18));
        project2.setBudget(9000.00);
        project2.setStatus(ProjectStatus.OPEN);
        project2.setCustomer(customer);

        project.setFreelancer(freelancer);
        project2.setFreelancer(freelancer);

        assertEquals(freelancer, project.getFreelancer());
        assertEquals(freelancer, project2.getFreelancer());

        System.out.println("project1: " + project.getTitle());
        System.out.println("project2: " + project2.getTitle());
    }
}