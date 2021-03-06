package it.academy.rest;

import it.academy.RestTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ContextConfiguration(classes = RestTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class VisitorCountRestServiceTest {

    public static final Logger log = Logger.getLogger(VisitorCountRestServiceTest.class.getName());

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void updateVisitorCount() throws Exception {
        final ExecutorService executorService =
                Executors.newFixedThreadPool(1000);

        for (AtomicInteger i = new AtomicInteger(); i.get() < 1000; i.getAndIncrement()) {
            executorService.submit(() -> {
                try {
                    mockMvc
                            .perform(put("/visitor_count")).andReturn();
                } catch (Exception e) {
                    e.printStackTrace();
                    i.getAndDecrement();
                }
            }
        );
        }

        final MvcResult mvcResult = mockMvc
                .perform(get("/visitor_count")).andReturn();
        final String content = mvcResult.getResponse().getContentAsString();
        log.info(content);
        assertEquals("1000", content);
    }

    @Test
    public void readVisitorCount() {
    }
}