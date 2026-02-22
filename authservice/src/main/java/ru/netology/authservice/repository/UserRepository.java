package ru.netology.authservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.authservice.model.Authorities;

import java.util.*;

@Repository
public class UserRepository {

    // user -> (password, authorities)
    private final Map<String, UserRecord> users = new HashMap<>();

    public UserRepository() {
        users.put("ivan", new UserRecord("123", List.of(Authorities.READ)));
        users.put("writer", new UserRecord("qwerty", List.of(Authorities.READ, Authorities.WRITE)));
        users.put("admin", new UserRecord("admin", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        UserRecord record = users.get(user);
        if (record == null) {
            return Collections.emptyList();
        }
        if (!record.password().equals(password)) {
            return Collections.emptyList();
        }
        return record.authorities();
    }

    private record UserRecord(String password, List<Authorities> authorities) {}
}