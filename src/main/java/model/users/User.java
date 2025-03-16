package model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

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