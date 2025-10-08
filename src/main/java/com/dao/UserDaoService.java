package com.dao;

import com.bean.Users;
import com.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<Users> listUsers = new ArrayList<Users>();
    private static int userCounts = 0;

    static {
        listUsers.add(new Users(++userCounts, "Adam", LocalDate.now().minusYears(30)));
        listUsers.add(new Users(++userCounts, "John", LocalDate.now().minusYears(32)));
        listUsers.add(new Users(++userCounts, "Bob The Builder", LocalDate.now().minusYears(25)));
    }

    public List<Users> getUsers() {
        return listUsers;
    }

    // UPDATED METHOD
    public Users getUser(int id) {
        Predicate<? super Users> predicate = user -> user.getId().equals(id);

        // The findFirst() method returns an Optional.
        // orElseThrow() will get the user if present, or throw the exception if not.
        return listUsers.stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public Users saveUsers(Users user){
        user.setId(++userCounts);
        listUsers.add(user);
        return user;
    }

    public void deleteById(int id){
        Predicate<? super Users> predicate = user -> user.getId().equals(id);
        listUsers.removeIf(predicate);
    }

}