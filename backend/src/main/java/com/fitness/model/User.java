package com.fitness.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * User entity representing system users
 */
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_user_email", columnList = "email"),
    @Index(name = "idx_user_username", columnList = "username")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", unique = true, nullable = false, length = 50)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "height") // in centimeters
    private Integer height;

    @Column(name = "weight") // in kilograms
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "fitness_level")
    private FitnessLevel fitnessLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_goals", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "goal")
    private Set<FitnessGoal> fitnessGoals = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    @Column(name = "is_enabled")
    @JsonIgnore
    private Boolean enabled = true;

    @Column(name = "is_account_non_expired")
    @JsonIgnore
    private Boolean accountNonExpired = true;

    @Column(name = "is_account_non_locked")
    @JsonIgnore
    private Boolean accountNonLocked = true;

    @Column(name = "is_credentials_non_expired")
    @JsonIgnore
    private Boolean credentialsNonExpired = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "timezone", length = 50)
    private String timezone = "UTC";

    @Column(name = "preferred_language", length = 10)
    private String preferredLanguage = "en";

    // Workout preferences
    @Column(name = "preferred_workout_duration") // in minutes
    private Integer preferredWorkoutDuration;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_workout_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "day_of_week")
    private Set<String> preferredWorkoutDays = new HashSet<>();

    @Column(name = "notification_enabled")
    private Boolean notificationEnabled = true;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<WorkoutSession> workoutSessions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserProgress> progressRecords = new ArrayList<>();

    // UserDetails implementation
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // Helper methods
    public String getFullName() {
        return (firstName != null ? firstName : "") + 
               (lastName != null ? " " + lastName : "").trim();
    }

    public Integer getAge() {
        if (dateOfBirth == null) return null;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public Double getBMI() {
        if (height == null || weight == null || height == 0) return null;
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    // Enums
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum FitnessLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }

    public enum FitnessGoal {
        WEIGHT_LOSS, MUSCLE_GAIN, STRENGTH_BUILDING, 
        ENDURANCE, FLEXIBILITY, GENERAL_FITNESS, 
        REHABILITATION, SPORTS_SPECIFIC
    }

    public enum Role {
        USER, TRAINER, ADMIN
    }
} 