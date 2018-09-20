package edu.hawaii.its.api.access;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.controller.WithMockUhUser;

@ActiveProfiles("localTest")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class UserContextServiceTest {

    @Autowired
    private UserContextService userContextService;

    @Test
    @WithMockUhUser(username = "admin", roles = { "ROLE_ADMIN" })
    public void basics() {
        assertThat(userContextService.getCurrentUhuuid(), equalTo("12345678L"));
        assertThat(userContextService.getCurrentUsername(), equalTo("admin"));
        assertThat(userContextService.toString(), startsWith("UserContextServiceImpl"));

        User user = userContextService.getCurrentUser();
        assertNotNull(user);
        assertThat(user.getUhuuid(), equalTo("12345678L"));
        assertThat(user.getUsername(), equalTo("admin"));

        userContextService.setCurrentUhuuid("87654321L");
        assertThat(userContextService.getCurrentUhuuid(), equalTo("87654321L"));
    }
}
