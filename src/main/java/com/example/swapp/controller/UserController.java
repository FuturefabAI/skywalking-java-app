package com.example.swapp.controller;

import com.example.swapp.User;
import com.example.swapp.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("SameRetureValue")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final int sleepMin = 500;
    private final int sleepMax = 1000;

    @GetMapping("/info")
    public String info() throws InterruptedException {
        Thread.sleep(randomSleepLoop(sleepMin, sleepMax));
        log.info("logback message==> now: {}", System.currentTimeMillis());
        return "info";
    }

    @GetMapping("/user")
    public List<User> usersList() throws InterruptedException {
        Thread.sleep(randomSleepLoop(sleepMin, sleepMax));
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User users(
            @RequestBody String name
    ) throws InterruptedException {
        Thread.sleep(randomSleepLoop(sleepMin, sleepMax));
        return userRepository.save(User.builder().name(name).build());
    }

    @PostMapping("/correlation")
    public String correlation() throws InterruptedException {
        Thread.sleep(randomSleepLoop(sleepMin, sleepMax));
        TraceContext.putCorrelation("PROVIDER_KEY", "provider");

        return TraceContext.getCorrelation("CONSUMER_KEY").orElse("") + "-"
                + TraceContext.getCorrelation("MIDDLE_KEY").orElse("") + "-"
                + TraceContext.getCorrelation("PROVIDER_KEY").orElse("");
    }

    private long randomSleepLoop(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
