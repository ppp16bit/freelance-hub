package model.projects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.users.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    @Column(columnDefinition = "TEXT")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Deadline is mandatory")
    private LocalDate term;

    @NotNull(message = "Budget is mandatory")
    @PositiveOrZero(message = "Budget must be positive or zero")
    private double budget;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is mandatory")
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User customer;
}
