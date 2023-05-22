package ru.oreshnikova.marketplace.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Role {
  @Id
  private Long id;
  private String name;
  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;
}