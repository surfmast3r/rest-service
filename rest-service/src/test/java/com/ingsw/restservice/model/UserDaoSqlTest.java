package com.ingsw.restservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoSqlTest {
    static Users user;
    static UserDaoSql userDaoSql;
    @BeforeAll
    static void createUser(){
       user = new Users();
       userDaoSql=new UserDaoSql();
    }

    /*BlackBox validateUserData*/
    @Test
    void validateUserDataWithUsernameLessThen6AndCorrectEmailAndPassword() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfg");
        user.setPwd("12345678");
        assertEquals(1, userDaoSql.validateUserData(user),"Username lenght < 6");

    }
    @Test
    void validateUserDataWithUsernameGreaterThen20AndCorrectEmailAndPassword() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjklqwertyuiopzx");
        user.setPwd("12345678");
        assertEquals(1, userDaoSql.validateUserData(user),"Username lenght > 20");

    }
    @Test
    void validateUserDataWithPasswordLessThen8AndCorrectEmailAndUsername() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjk");
        user.setPwd("1234567");
        assertEquals(2, userDaoSql.validateUserData(user),"Username lenght > 8");

    }
    @Test
    void validateUserDataWithPasswordGreaterThen20AndCorrectEmailAndUsername() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjk");
        user.setPwd("123456789123456789123");
        assertEquals(2, userDaoSql.validateUserData(user),"Username lenght > 20");

    }
    @Test
    void validateUserDataWithWrongEmailAndCorrectUsernameAndPassword() {
        user.setEmail("emailgmail.com");
        user.setNickname("asdfghjk");
        user.setPwd("12345678");

        assertEquals(3, userDaoSql.validateUserData(user),"Wrong Email");

    }

    @Test
    void validateUserDataWithCorrectEmailUsernameLength6AndCorrectPassword() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfgh");
        user.setPwd("12345678");
        assertEquals(0, userDaoSql.validateUserData(user),"Correct");

    }

    @Test
    void validateUserDataWithCorrectEmailUsernameLength20AndCorrectPassword() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjklqwertyuiopz");
        user.setPwd("12345678");
        assertEquals(0, userDaoSql.validateUserData(user),"Correct");

    }

    @Test
    void validateUserDataWithCorrectEmailCorrectUsernameAndPasswordLength8() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjklqwertyuiopz");
        user.setPwd("12345678");
        assertEquals(0, userDaoSql.validateUserData(user),"Correct");

    }
    @Test
    void validateUserDataWithCorrectEmailCorrectUsernameAndPasswordLength20() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjklqwertyuiopz");
        user.setPwd("12345678912345678912");
        assertEquals(0, userDaoSql.validateUserData(user),"Correct");

    }

    @Test
    void validateUserDataWithCorrectEmailUsernameAndPassword() {
        user.setEmail("email@gmail.com");
        user.setNickname("asdfghjklqas");
        user.setPwd("1234567890123");
        assertEquals(0, userDaoSql.validateUserData(user),"Correct");

    }
}