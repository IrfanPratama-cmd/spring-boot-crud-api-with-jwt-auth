package jwtspringproduct.jwtspringproduct.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static jwtspringproduct.jwtspringproduct.model.Permission.ADMIN_CREATE;
import static jwtspringproduct.jwtspringproduct.model.Permission.ADMIN_READ;
import static jwtspringproduct.jwtspringproduct.model.Permission.ADMIN_UPDATE;
import static jwtspringproduct.jwtspringproduct.model.Permission.ADMIN_DELETE;
import static jwtspringproduct.jwtspringproduct.model.Permission.USER_DELETE;
import static jwtspringproduct.jwtspringproduct.model.Permission.USER_UPDATE;
import static jwtspringproduct.jwtspringproduct.model.Permission.USER_READ;
import static jwtspringproduct.jwtspringproduct.model.Permission.USER_CREATE;;


@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  USER_READ,
                  USER_UPDATE,
                  USER_DELETE,
                  USER_CREATE
          )
  ),
  USERS(
          Set.of(
                  USER_READ,
                  USER_UPDATE,
                  USER_DELETE,
                  USER_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}