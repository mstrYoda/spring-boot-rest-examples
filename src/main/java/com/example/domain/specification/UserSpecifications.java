package com.example.domain.specification;

import com.example.domain.user.User;
import com.example.domain.user.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> ageGreaterThen(int age) {
        return (root, query, cb) -> cb.greaterThan(root.get(User_.age), age);
    }

    public static Specification<User> emailEqual(String email) {
        return (root, query, cb) -> cb.equal(root.get(User_.email), email);
    }
}
