package testng;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = {"classpath:/META-INF/spring-standalone-beans.xml"})
@Configuration
public class BaseTestNGTest extends AbstractTestNGSpringContextTests {

}
