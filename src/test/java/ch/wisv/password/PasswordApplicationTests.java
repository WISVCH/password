package ch.wisv.password;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PasswordApplication.class)
@WebAppConfiguration
public class PasswordApplicationTests {

    @Test
    @SuppressWarnings("EmptyMethod")
    public void contextLoads() {
    }

}
