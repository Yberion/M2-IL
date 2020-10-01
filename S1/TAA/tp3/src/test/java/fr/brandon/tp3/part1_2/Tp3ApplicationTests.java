package fr.brandon.tp3.part1_2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Tp3ApplicationTests
{
    private ApplicationContext ctx;

    public Tp3ApplicationTests(ApplicationContext ctx)
    {
        super();
        this.ctx = ctx;
    }

    @Test
    void testContextLoads() throws Exception
    {
        assertThat(this.ctx).isNotNull();
        assertThat(this.ctx.containsBean("helloWorldService")).isTrue();
        assertThat(this.ctx.containsBean("clientImpl")).isTrue();
        assertThat(this.ctx.containsBean("storeImpl")).isTrue();
    }
}
