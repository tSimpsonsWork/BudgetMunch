package com.example.project2;

import com.example.project2.entity.User;
import com.example.project2.entity.repository.UserRepository;
import com.example.project2.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void testSaveUser() {
        // Arrange
        List<User> users = List.of(
                new User(1L, "JohnDoe", "JohnISGreat", "john@example.com","cat"),
                new User(2L, "JaneDoe", "JaneIsAwesome", "jane@example.com","dog")
        );

        // Act
        userService.saveUser(users);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).saveAll(users);
    }

    @Test
    void testExistsByUsername() {
        // Arrange
        String username = "JohnDoe";
        Mockito.when(userRepository.findByUserName(username)).thenReturn(Optional.of(new User(1L,"JohnWick", username, "john@example.com","123")));

        // Act
        boolean exists = userService.existsByUsername(username);

        // Assert
        assertTrue(exists);
        Mockito.verify(userRepository, Mockito.times(1)).findByUserName(username);
    }

    @Test
    void testDeleteAllUser() {
        // Act
        userService.deleteAllUser();

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).deleteAll();
    }

    @Test
    void testGetUsers() {
        // Arrange
        List<User> mockUsers = List.of(
                new User(1L,"JohnWick", "JohnDoe", "john@example.com","cat123"),
                new User(2L,"JaneIsWick" ,"JaneDoe", "jane@example.com","dog123")
        );
        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);

        // Act
        List<User> users = userService.getUsers();

        // Assert
        assertEquals(2, users.size());
        assertEquals("JohnDoe", users.get(0).getUserName());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testExistsByEmail() {
        // Arrange
        String email = "jane@example.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User(2L, "JaneY" , "JaneDoe", email,"caller")));

        // Act
        boolean exists = userService.existsByEmail(email);

        // Assert
        assertTrue(exists);
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
    }

}
