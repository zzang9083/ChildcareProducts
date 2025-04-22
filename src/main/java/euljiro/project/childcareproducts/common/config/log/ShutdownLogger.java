package euljiro.project.childcareproducts.common.config.log;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShutdownLogger {
    @PreDestroy
    public void logShutdown() {
        log.debug("🛑 앱 종료 감지됨! 내가 왜 죽었는지 로그 봐라");
    }
}
