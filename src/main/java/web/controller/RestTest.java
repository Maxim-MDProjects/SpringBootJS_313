package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    /*@GetMapping(value = "/list")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }*/
    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
       /* try {
            List<User> userList = userService.allUsers();

            return (List<User>) new ResponseEntity<String>("List User!",
                    (MultiValueMap<String, String>) userList, HttpStatus.OK);
        }catch(Exception e) {
            return (List<User>) new ResponseEntity<String>("Fail!",
                    null, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        final List<User> userList = userService.allUsers();

        return userList != null &&  !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    public void addNew(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        userService.add(user);
    }

   /* @GetMapping(value = "/edit/{id}")
    //public Optional<User> editOne(@RequestBody User user, Long id) {
    public Optional<User> editOne(@PathVariable("id") Long id) {
        //return userService.getById(id);
        return userService.getById(id);
    }*/
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        System.out.println("id="+id);
        userService.getById(id);
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
