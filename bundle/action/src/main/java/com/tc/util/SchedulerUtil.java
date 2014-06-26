/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.util;

public class SchedulerUtil {

    public static Integer getIndex(Long timeDifference, Long timeInterval,
            String timeUnit, int listSize, int lot) {

        Integer index = null;

        timeDifference = calculateTime(timeDifference, timeUnit);

        if (timeDifference / timeInterval <= listSize / lot) {
            index = (int) (timeDifference / timeInterval);
        } else {
            if (listSize % lot == 0) {
                index = listSize / lot;
                index = index - 1;
            } else {
                index = listSize / lot;
            }
        }
        return index;

    }

    public static Long calculateTime(Long timeDifference, String timeUnit) {

        if (timeUnit.equals(Constants.CAROUSEL.SECONDS)) {

            timeDifference = timeDifference / (Constants.THOUSAND);

        } else if (timeUnit.equals(Constants.CAROUSEL.MINUTES)) {
            timeDifference = timeDifference
                    / (Constants.THOUSAND * Constants.SIXTY);
        } else if (timeUnit.equals(Constants.CAROUSEL.HOURS)) {
            timeDifference = timeDifference
                    / (Constants.THOUSAND * Constants.SIXTY * Constants.SIXTY);
        } else if (timeUnit.equals(Constants.CAROUSEL.DAYS)) {
            timeDifference = timeDifference
                    / (Constants.THOUSAND * Constants.SIXTY
                    * Constants.SIXTY * Constants.TWENTY_FOUR);
        }

        return timeDifference;

    }

}
