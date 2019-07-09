package com.zenkou.paymentsystem.test.dao;

import com.google.common.collect.Lists;
import com.zenkou.paymentsystem.database.entity.db.User;
import com.zenkou.paymentsystem.database.repository.UserRepository;
import com.zenkou.paymentsystem.test.BaseDataBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UserRepositoryTest extends BaseDataBaseTest {

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkSaveUser() {
        user = userRepository.save(creatorUtil.getUser());
        assertNotNull(user);
    }

    @Test(dependsOnMethods = "checkSaveUser")
    public void checkGetUser() {
        assertTrue(userRepository.existsById(user.getId()));
    }

    @Test(dependsOnMethods = "checkGetUser")
    public void checkUpdateUser() {
        String newName = "test";
        user.setName(newName);
        userRepository.save(user);
        Optional<User> gotUser = userRepository.findById(user.getId());
        String actName = gotUser.isPresent() ? gotUser.get().getName() : "";
        assertEquals(actName, newName);
    }

    @Test(dependsOnMethods = "checkUpdateUser")
    public void checkDeleteUser() {
        userRepository.delete(user);
        Optional<User> userOptional = userRepository.findById(user.getId());
        assertFalse(userOptional.isPresent());
    }

    @Test(dependsOnMethods = "checkDeleteUser")
    public void checkGetAllUsers() {
        List<User> users = creatorUtil.getUsers();
        users.forEach(user -> userRepository.save(user));
        List<User> gotUsers = Lists.newArrayList(userRepository.findAll());
        assertEquals(users.size(), gotUsers.size());
    }

    @AfterClass
    public void cleanDataBase() {
        databaseHelper.cleanDatabase();
        user.setId(null);
    }
}
