package com.muksia.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FutbinChallengeHolder {

    private static List<String> challenges = new ArrayList<>();

    public List<String> getCurrentChallenges() {
        return challenges;
    }

    public void addChallenge(final String challenge) {
        challenges.add(challenge);
    }

    public void cleanChallenges() {
        challenges = new ArrayList<>();
    }

    public boolean processNewChallenges(final List<String> newChallenges) {
        if (challenges.containsAll(newChallenges)){
            challenges.retainAll(newChallenges);
            return false;
        }
        challenges = newChallenges;
        return true;

    }
}
