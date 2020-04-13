package posmotriKa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import posmotriKa.config.ApplicationContextConfig;
import posmotriKa.repositories.UserRepository;
import posmotriKa.services.RegisterService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);

        RegisterService registerService = context.getBean(RegisterService.class);
UserRepository userRepository = context.getBean(UserRepository.class);

        //registerService.register(RegisterDto.builder().email("dsfgdfg").password("sdlfkjglsdk").username("dfkslgsdl").build());

    }
}
