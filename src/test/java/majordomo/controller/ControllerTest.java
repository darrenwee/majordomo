package majordomo.controller;

import majordomo.service.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    @Test
    public void getCommandsNoCommands(){
        String easyTest = "a string with\n\n multiple lines\n but no worrying things\r\n\r\n yay";
        String noCommands = "/notACommand";
        String containsButNotEqual = "/ccMe";
        String commandInMiddle = "A string with a command \retest in the middle of the line";
        String spaceBeforeCommand = " /cc me";
        assertEquals(new ArrayList<>(), new Controller().getCommands(easyTest));
        assertEquals(new ArrayList<>(), new Controller().getCommands(noCommands));
        assertEquals(new ArrayList<>(), new Controller().getCommands(containsButNotEqual));
        assertEquals(new ArrayList<>(), new Controller().getCommands(commandInMiddle));
        assertEquals(new ArrayList<>(), new Controller().getCommands(spaceBeforeCommand));
    }

    @Test
    public void getCommandRequest(){
        String request = "\n\nthing with words\r\n/cc @userabc @userdef @user1";
        String[] arguments = {"cc", "@userabc @userdef @user1"};
        Service reviewRequester = new ReviewRequester(arguments);
        assertEquals(Arrays.asList(reviewRequester), new Controller().getCommands(request));
    }

    @Test
    public void getCommandUnrequest(){
        String unrequest = "49501834%23940234902i\n\n/uncc @user1 @userdef @userabc";
        String[] arguments = {"uncc", "@userabc @userdef @user1"};
        Service reviewRequester = new ReviewRequester(arguments);
        assertEquals(Arrays.asList(reviewRequester), new Controller().getCommands(unrequest));
    }

    @Test
    public void getCommandRetest(){
        String retest = "This doesn't \n\nwork right now so I'm going to get you to\n\n/retest";
        String[] arguments = {"retest"};
        Service retester = new Retester();
        assertEquals(Arrays.asList(retester), new Controller().getCommands(retest));
    }

    @Test
    public void getCommandOngoing(){
        String ongoing = "/ongoing";
        String[] arguments = {"ongoing"};
        Service labeller = new Labeller(arguments);
        assertEquals(Arrays.asList(labeller), new Controller().getCommands(ongoing));
    }

    @Test
    public void getCommandAssign(){
        String assign = "/assign @userabc @userdef @user1";
        String[] arguments = {"assign", "@userabc @userdef @user1"};
        Service assigner = new Assigner(arguments);
        assertEquals(Arrays.asList(assigner), new Controller().getCommands(assign));
    }

    @Test
    public void getCommandUnassign(){
        String unassign = "/unassign @userabc @userdef @user1";
        String[] arguments = {"unassign", "@userabc @userdef @user1"};
        Service unassigner = new Assigner(arguments);
        assertEquals(Arrays.asList(unassigner), new Controller().getCommands(unassign));
    }

    @Test
    public void getCommandHold(){
        String hold = "/onhold";
        String[] arguments = {"onhold"};
        Service holder = new Labeller(arguments);
        assertEquals(Arrays.asList(holder), new Controller().getCommands(hold));
    }

    @Test
    public void getCommandMerge(){
        String merge = "/ok-to-merge";
        String[] arguments = {"ok-to-merge"};
        Service merger = new Merger();
        assertEquals(Arrays.asList(merger), new Controller().getCommands(merge));
    }


}
