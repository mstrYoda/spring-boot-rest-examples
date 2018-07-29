package com.example.infrastructure.repository;

import com.example.domain.user.Address;
import com.example.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository sut;

    @Test
    public void it_should_save_user_with_address() {
        User user = new User();
        Address address = new Address();
        address.setAddressText("test address");
        user.setAddress(address);

        user = entityManager.persistAndFlush(user);

        assertThat(sut.findById(user.getId()).get()).isEqualTo(user);
    }
}