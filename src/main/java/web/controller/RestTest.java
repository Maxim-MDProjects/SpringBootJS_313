package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/adminrest")
public class RestTest {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RestTest(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    //вывод всех
    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> userList = userService.allUsers();
        return userList != null &&  !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //добавление нового
    @PostMapping(value = "/add")
    public ResponseEntity<Object> addNew(@RequestBody User user,
                                         @RequestParam(value = "roles", required = false) String[] roles) {
        Set<Role> rolesArray = new HashSet<>();
        for (String role : roles) {
            Optional<Role> currentRole = userService.getRoleByRoleName(role);
            rolesArray.add(currentRole.get());
        }
        user.setRoles(rolesArray);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //получение одного юзера
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Optional<User>> read(@PathVariable(name = "id") Long id) {
        final Optional<User> client = userService.getById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //удаление юзера
    @GetMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getById(id);
        userService.delete(user.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //обновление юзера
    @PostMapping(value = "/edit")
    public ResponseEntity<Object> editUser(@RequestBody User user,
                                         @RequestParam(value = "roles", required = false) String[] roles) {
        Set<Role> rolesArray = new HashSet<>();
        for (String role : roles) {
            Optional<Role> currentRole = userService.getRoleByRoleName(role);
            rolesArray.add(currentRole.get());
        }
        user.setRoles(rolesArray);

        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            Optional<User> newUser = userService.getById(user.getId());
            user.setPassword(newUser.get().getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}



//
//    @GetMapping(value = "/user/{id}")
//    public ResponseEntity<Optional<User>> getOneUser(@PathVariable(name = "id") Long id) {
//        final Optional<User> client = userService.getById(id);
//        return client != null
//                ? new ResponseEntity<>(client, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }