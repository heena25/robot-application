package com.xebia.robot.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.xebia.robot.application.factory.RobotFactory;
import com.xebia.robot.application.models.Robot;
import com.xebia.robot.application.models.Robot.Color;
import com.xebia.robot.application.response.RobotActivityReponse;
import com.xebia.robot.application.service.RobotService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class RobotControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RobotFactory robotFactory;
    
    @MockBean
    private RobotService robotService;
    
    @Test
    public void testCreateDefaultRobot() throws Exception {
        Robot robot = this.restTemplate.postForObject("/api/robot/v1.0/create-default-robot", null, Robot.class);
        assertNotNull(robot);
    }
    
    @Test
    public void testCreateRobot() throws Exception {
        Robot robot = new Robot("abcd",100, Color.GREEN, 2, 5);
        given(this.robotFactory.createNewRobot(robot)).willReturn(robot);
        Robot responseRobot = this.restTemplate.postForObject("/api/robot/v1.0/create-robot", robot, Robot.class);
        assertEquals(robot.getName(), responseRobot.getName());
    }
    
    @Test
    public void testWalk() throws Exception {
        RobotActivityReponse robotActivityReponse = new RobotActivityReponse();
        Robot robot = new Robot("abcd",100, Color.GREEN, 2, 5);
        robotActivityReponse.setRobot(robot);
        given(this.robotFactory.getExistingRobot("ANCD")).willReturn(robot);
        given(this.robotService.carry(5, robot)).willReturn(robotActivityReponse);
        RobotActivityReponse reponseRobotActivityReponse = this.restTemplate.getForObject("/api/robot/v1.0/walk/ANCD/5", RobotActivityReponse.class);
        assertEquals(reponseRobotActivityReponse.getRobot(), null);
    }
}
