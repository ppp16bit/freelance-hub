package model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Main role is mandatory")
    private UserRole mainRole;

    public String getCnpj() {
        return cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull(message = "Main role is mandatory") UserRole getMainRole() {
        return mainRole;
    }

    public void setMainRole(@NotNull(message = "Main role is mandatory") UserRole mainRole) {
        this.mainRole = mainRole;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Password is mandatory") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") String password) {
        this.password = password;
    }

    private void validateCpfAndCnpj() {
        if (this.cpf != null || this.cnpj != null) {
            throw new IllegalArgumentException("User already has a registered identifier");
        }
    }

    public void setCpf(String cpf) {
        validateCpfAndCnpj();
        this.cpf = cpf;
    }

    public void setCnpj(String cnpj) {
        validateCpfAndCnpj();
        this.cnpj = cnpj;
    }
}