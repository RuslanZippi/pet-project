package main.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permissions.USER)),
    MODERATOR(Set.of(Permissions.USER, Permissions.MODERATOR));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermissions()))
                .collect(Collectors.toSet());
    }
}
