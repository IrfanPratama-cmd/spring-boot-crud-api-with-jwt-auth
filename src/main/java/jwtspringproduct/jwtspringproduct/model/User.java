package jwtspringproduct.jwtspringproduct.model;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "email") 
    })
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private String firstname;
    private String lastname;
  
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
  
    @NotBlank
    @Size(max = 120)
    private String password;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.updatedAt == null) updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

   
    @Enumerated(EnumType.STRING)
    private Role role;
  
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return role.getAuthorities();
    }
      @Override
      public String getPassword() {
        return password;
      }
    
      @Override
      public String getUsername() {
        return email;
      }

      public UUID getId() {
        return id;
      }
    
      @Override
      public boolean isAccountNonExpired() {
        return true;
      }
    
      @Override
      public boolean isAccountNonLocked() {
        return true;
      }
    
      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }
    
      @Override
      public boolean isEnabled() {
        return true;
      }
}
