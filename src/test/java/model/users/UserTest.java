package model.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        System.out.println("User created");
    }

    @Test
    void testSetCpf() {
        user.setCpf("111.111.111-01");
        assertThat(user.getCpf()).isEqualTo("111.111.111-01");
        System.out.println("cpf: " + user.getCpf());
    }

    @Test
    void testSetCnpj() {
        user.setCnpj("11.111.111/0001-11");
        assertThat(user.getCnpj()).isEqualTo("11.111.111/0001-11");
        System.out.println("cnpj: " + user.getCnpj());
    }

    @Test
    void testSetCpfAndCnpjThrowsException() {
        user.setCpf("111.111.111-01");
        assertThatThrownBy(() -> user.setCnpj("11.111.111/0001-11"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User already has a registered identifier");
    }

    @Test
    void testSetCnpjAndCpfThrowsException() {
        user.setCnpj("11.111.111/0001-11");
        assertThatThrownBy(() -> user.setCpf("111.111.111-01"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User already has a registered identifier");
    }

    @Test
    void testSetCpfWithNull() {
        user.setCpf(null);
        assertThat(user.getCpf()).isNull();
    }

    @Test
    void testSetCnpjWithNull() {
        user.setCnpj(null);
        assertThat(user.getCnpj()).isNull();
    }

    @Test
    void testSetName() {
        user.setName("Pedro Henrique");
        assertThat(user.getName()).isEqualTo("Pedro Henrique");
        System.out.println("name: " + user.getName());
    }

    @Test
    void testSetEmail() {
        user.setEmail("ppp123@gmail.com");
        assertThat(user.getEmail()).isEqualTo("ppp123@gmail.com");
        System.out.println("email: " + user.getEmail());
    }

    @Test
    void testSetPasssword() {
        user.setPassword("ppp123");
        assertThat(user.getPassword()).isEqualTo("ppp123");
        System.out.println("password: " + user.getPassword());
    }

    @Test
    void testSetCustomerMainRole() {
        user.setMainRole(UserRole.CUSTOMER);
        assertThat(user.getMainRole()).isEqualTo(UserRole.CUSTOMER);
        System.out.println("role: " + user.getMainRole());
    }

    @Test
    void testSetFreelancerMainRole() {
        user.setMainRole(UserRole.FREELANCER);
        assertThat(user.getMainRole()).isEqualTo(UserRole.FREELANCER);
        System.out.println("role: " + user.getMainRole());
    }

    @Test
    void testSetId() {
        UUID id = UUID.randomUUID();
        user.setId(id);
        assertThat(user.getId()).isEqualTo(id);
        System.out.println("id: " + id);
    }
}