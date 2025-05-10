package com.saandeep.govrn.util.enums;

public enum ProjectStatus {
    VOTING_NOT_STARTED,
    VOTING_IN_PROGRESS,
    VOTING_ENDED,
    UNDER_REVIEW,
    PROJECT_IN_PROGRESS,
    PROJECT_COMPLETED,
    ABANDONED;

    public static ProjectStatus getStageFromInteger(int s) {
        switch (s) {
            case 0:
                return VOTING_NOT_STARTED;
            case 1:
                return VOTING_IN_PROGRESS;
            case 2:
                return VOTING_ENDED;
            case 3:
                return UNDER_REVIEW;
            case 4:
                return PROJECT_IN_PROGRESS;
            case 5:
                return PROJECT_COMPLETED;
            case 6:
                return ABANDONED;
        }
        return null;
    }
}
