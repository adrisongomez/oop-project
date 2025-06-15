package test.oopecommerce.__fixtures__;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.oopecommerce.models.users.User;
import com.oopecommerce.repositories.IUserRepository;

@Component
public class InMemoryUserRepository implements IUserRepository {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();
    private final Map<String, User> emailIndex = new ConcurrentHashMap<>();
    private final Map<String, List<User>> nameIndex = new ConcurrentHashMap<>();

    public InMemoryUserRepository() {
        // Mock data
        User u1 = new User(UUID.fromString("00000000-0000-0000-0000-000000000001"), "john@example.com", "hash1",
                "John");
        User u2 = new User(UUID.fromString("00000000-0000-0000-0000-000000000002"), "jane@example.com", "hash2",
                "Jane");
        save(u1);
        save(u2);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(emailIndex.get(email.toLowerCase()));
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
        emailIndex.put(user.getEmail().toLowerCase(), user);
        nameIndex.computeIfAbsent(user.getName().toLowerCase(), k -> new ArrayList<>()).add(user);
    }

    @Override
    public void delete(UUID id) {
        User removed = users.remove(id);
        if (removed != null) {
            emailIndex.remove(removed.getEmail().toLowerCase());
            List<User> list = nameIndex.get(removed.getName().toLowerCase());
            if (list != null) {
                list.removeIf(u -> u.getId().equals(id));
                if (list.isEmpty()) {
                    nameIndex.remove(removed.getName().toLowerCase());
                }
            }
        }
    }

    @Override
    public Iterable<User> findAll() {
        return users.values();
    }

    @Override
    public Iterable<User> search(String query, int limit, int offset) {
        List<User> results = new ArrayList<>();
        if (query != null) {
            String q = query.toLowerCase();
            nameIndex.forEach((name, list) -> {
                if (name.contains(q)) {
                    results.addAll(list);
                }
            });
            emailIndex.forEach((e, u) -> {
                if (e.contains(q) && !results.contains(u)) {
                    results.add(u);
                }
            });
        } else {
            results.addAll(users.values());
        }
        int start = Math.max(0, offset);
        int end = Math.min(results.size(), start + limit);
        if (start > end) {
            return new ArrayList<>();
        }
        return results.subList(start, end);
    }
}
