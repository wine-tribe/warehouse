package backend.enumeration;

import org.springframework.data.jpa.repository.JpaRepository;

public enum EntityType {
    ORDER("O-"),
    WAREHOUSE("W-");

    private final String prefix;

    EntityType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}


