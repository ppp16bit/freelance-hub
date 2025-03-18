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

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer;

    @NotNull(message = "Budget is mandatory")
    @PositiveOrZero(message = "Budget must be positive or zero")
    public double getBudget() {
        return budget;
    }

    public void setBudget(@NotNull(message = "Budget is mandatory") @PositiveOrZero(message = "Budget must be positive or zero") double budget) {
        this.budget = budget;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
    }

    public @NotBlank(message = "Description is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is mandatory") String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull(message = "Status is mandatory") ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status is mandatory") ProjectStatus status) {
        this.status = status;
    }

    public @NotNull(message = "Deadline is mandatory") LocalDate getTerm() {
        return term;
    }

    public void setTerm(@NotNull(message = "Deadline is mandatory") LocalDate term) {
        this.term = term;
    }

    public @NotBlank(message = "Title is mandatory") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is mandatory") String title) {
        this.title = title;
    }
}
