package com.bridgelabz.GreetingApp.service;

import com.bridgelabz.GreetingApp.model.Greeting;
import com.bridgelabz.GreetingApp.model.User;
import com.bridgelabz.GreetingApp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService{
    private static final String template = "Hello %s %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingRepository repository;
    @Override
    public Greeting addGreeting(User user) {
        String message = String.format(template, (user.getFirstName().isEmpty()) ? "Hello World" : user.getFirstName(),
                (user.getLastName().isEmpty()) ? "Hello World" : user.getLastName());
        return repository.save(new Greeting(counter.incrementAndGet(), message));
    }

    @Override
    public List<Greeting> getGreetings() {
        return repository.findAll();
    }

    @Override
    public Optional<Greeting> getGreetingById(long id) {
        return repository.findById(id);
    }

    @Override
    public Greeting postGreetings(User user) {
        String message = String.format(template, (user.getFirstName().isEmpty()) ? "Hello World" : user.getFirstName(),
                (user.getLastName().isEmpty()) ? "Hello World" : user.getLastName());
        return repository.save(new Greeting(counter.incrementAndGet(), message));
    }

    @Override
    public void deleteGreetingById(long id) {
        Greeting message  = repository.getOne(id);
        repository.delete(message);
    }

    @Override
    public Greeting updateGreetings(Greeting greeting) {
        repository.save(greeting);
        return greeting;
    }
}