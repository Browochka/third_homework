package org.example.synthetichumancorestarter;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commands")
@RequiredArgsConstructor
public class controller {
    private final comService comService;
    @PostMapping
    public void handleCommand(@RequestBody @Valid transferObject command) {
        comService.processCommand(command);
    }
}
