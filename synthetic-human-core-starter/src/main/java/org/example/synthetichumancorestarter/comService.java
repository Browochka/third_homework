package org.example.synthetichumancorestarter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.synthetichumancorestarter.audit.WeylandWatchingYou;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class comService {
    private final kafkaQueue queue;
    @WeylandWatchingYou
    public void processCommand(transferObject command) {
        switch (command.getPriority()) {
            case CRITICAL -> executeRightNow(command);
            case COMMON -> queue.unqueue(command);
        }
    }
    public void executeRightNow(transferObject command) {
        log.info("ЩАС ВЫПОЛНИИИМ");
    }
}
