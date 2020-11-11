package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/adminrest")
public class RestTest {

    private final UserServiceImpl userService;
    @Autowired
    public RestTest(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> userList = userService.allUsers();
        return userList != null &&  !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addNew(@RequestBody User user, @RequestParam("role") String[] role) {
        System.out.println(user.getName());
        System.out.println(role.toString());
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/edit/{id}")
    public ResponseEntity<Optional<User>> read(@PathVariable(name = "id") Long id) {
        final Optional<User> client = userService.getById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getById(id);
        userService.delete(user.get());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/list");
        return modelAndView;
    }*/

    @GetMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getById(id);
        userService.delete(user.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Optional<User>> getOneUser(@PathVariable(name = "id") Long id) {
        final Optional<User> client = userService.getById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
