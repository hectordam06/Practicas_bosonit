package com.bosonit.block6pathvariableheaders.controller;

import com.bosonit.block6pathvariableheaders.model.ResutlUser;
import com.bosonit.block6pathvariableheaders.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @PostMapping("/echo")
    public User echo(@RequestBody User user) {
        return user;
    }

    @GetMapping("/user/{id}")
    public Long getUserId(@PathVariable Long id) {
        return id;
    }

    @PutMapping("/post")
    public Map<String, String> putData(@RequestParam Map<String, String> params) {
        Map<String, String> response = new HashMap<>();
        response.putAll(params);
        return response;
    }

    @GetMapping("/header")
    public Map<String, String> getHeaders(@RequestHeader Map<String, String> headers) {
        Map<String, String> response = new HashMap<>();
        response.put("h1", headers.get("h1"));
        response.put("h2", headers.get("h2"));
        return response;
    }

    @PostMapping("/all")
    public ResutlUser getAllData(@RequestBody(required = false) String body,
                                 @RequestHeader Map<String, String> headers,
                                 @RequestParam Map<String, String> params) {
        ResutlUser result = new ResutlUser();
        result.setBody(body);
        result.setHeaders(new ArrayList<>(headers.values()));
        result.setRequestParams(new ArrayList<>(params.values()));
        return result;
    }
}
