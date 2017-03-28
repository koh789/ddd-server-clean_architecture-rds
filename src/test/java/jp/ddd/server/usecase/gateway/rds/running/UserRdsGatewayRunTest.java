package jp.ddd.server.usecase.gateway.rds.running;

import jp.ddd.server.usecase.gateway.rds.UserRdsGateway;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

/**
 * Created by noguchi_kohei 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class UserRdsGatewayRunTest {
    @Autowired
    private UserRdsGateway userRdsGateway;

    @Test
    public void getTest() {
        try {
            val resultOpt = userRdsGateway.getOpt("demo@gmail.com");
            System.out.println(resultOpt.isPresent());
        } catch (Exception e) {
            fail();
        }

    }
}
