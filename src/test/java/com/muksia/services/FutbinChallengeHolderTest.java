package com.muksia.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IZA10326 on 2017-01-12.
 */
public class FutbinChallengeHolderTest {

    private static final String CHALLENGE = "Challenge";
    private final FutbinChallengeHolder futbinChallengeHolder = new FutbinChallengeHolder();

    @Test
    public void shouldReturnListOfChallenges(){
        futbinChallengeHolder.cleanChallenges();
        List<String> challenges = futbinChallengeHolder.getCurrentChallenges();
        Assert.assertNotNull(challenges);
    }

    @Test
    public void shouldReturnChallengeIfWeAddIt(){
        futbinChallengeHolder.cleanChallenges();
        futbinChallengeHolder.addChallenge(CHALLENGE);
        List<String> challenges = futbinChallengeHolder.getCurrentChallenges();

        Assert.assertEquals(CHALLENGE, challenges.get(0));
    }

    @Test
    public void shouldClearAllTheChallengesWhenCleaned(){
        futbinChallengeHolder.cleanChallenges();
        futbinChallengeHolder.addChallenge(CHALLENGE);
        futbinChallengeHolder.cleanChallenges();

        Assert.assertTrue(CollectionUtils.isEmpty(futbinChallengeHolder.getCurrentChallenges()));
    }

    @Test
    public void shouldChangeChallengesIfDifferentAndReturnTrue(){
        futbinChallengeHolder.cleanChallenges();
        futbinChallengeHolder.addChallenge(CHALLENGE);
        futbinChallengeHolder.addChallenge("challenge2");

        final boolean result =
                futbinChallengeHolder.processNewChallenges(createChallengesWithTwoItems("test1", "test2"));

        List<String> challenges = futbinChallengeHolder.getCurrentChallenges();
        Assert.assertEquals("test1", challenges.get(0));
        Assert.assertEquals("test2", challenges.get(1));
        Assert.assertEquals(2, challenges.size());
        Assert.assertTrue(result);
    }

    @Test
    public void shouldChangeChallengesIfDifferentButNothingNewAndReturnFalse(){
        futbinChallengeHolder.cleanChallenges();
        futbinChallengeHolder.addChallenge(CHALLENGE);
        futbinChallengeHolder.addChallenge("challenge2");
        futbinChallengeHolder.addChallenge("challenge3");

        final boolean result =
                futbinChallengeHolder.processNewChallenges(createChallengesWithTwoItems("challenge2", "challenge3"));

        List<String> challenges = futbinChallengeHolder.getCurrentChallenges();
        Assert.assertEquals("challenge2", challenges.get(0));
        Assert.assertEquals("challenge3", challenges.get(1));
        Assert.assertEquals(2, challenges.size());
        Assert.assertFalse(result);
    }

    private List<String> createChallengesWithTwoItems(final String item1, final String item2){
        final List<String> challenges = new ArrayList<>();
        challenges.add(item1);
        challenges.add(item2);
        return challenges;
    }
}
