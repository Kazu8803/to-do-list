package com.toDoList.manager;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.toDoList.entity.Job;
import com.toDoList.entity.User;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UsersManager {

    @Getter
    private List<User> users = new ArrayList<User>();

    private JobListManager jobListManager = new JobListManager();

    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/hello")
    public String Hello () {
        return "Hello";
    }

    @PostMapping("/addUser")
    public void addUser (@RequestBody String name) {
        final User user = new User();
        user.setId(nextId.incrementAndGet());
        user.setName(name);
        users.add(user);
    }

    @GetMapping("/getUserFromList/{id}")
    public User getUserFromList (@PathVariable("id") long id) {
        for (User user : users) {
            if (user.getId() == id) {
                System.out.println(user.getName());
                return user;
            }
        }
        throw new IllegalArgumentException();
    }

    @GetMapping("/getJobsListFromList/{id}")
    public List<Job> getJobsListFromList (@PathVariable("id") Long id) {
        for (User user : users) {
            if (user.getId() == id) {
                System.out.println(user.getName());
                return user.getJobsList();
            }
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/addJobToUserList")
    public void addJobToUserList (@RequestBody ObjectNode objectNode) {
        Long userId = objectNode.get("str1").asLong();
        String description = objectNode.get("str2").asText();
        jobListManager.setJobsList(this.getJobsListFromList(userId));
        jobListManager.addJob(description);
    }

    @GetMapping("/getUsersList")
    public List<User> getUsersList () {
        return this.users;
    }

}
