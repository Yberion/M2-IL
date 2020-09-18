package fr.brandon.tp3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Tp3ApplicationTests
{
    @Autowired
    ApplicationContext ctx;

    @Test
    void testContextLoads() throws Exception
    {
        assertThat(this.ctx).isNotNull();
        assertThat(this.ctx.containsBean("helloWorldService")).isTrue();
        assertThat(this.ctx.containsBean("client")).isTrue();
    }
}
