package com.nestos.weathertrial.domain;

import java.util.Date;

/**
 * Time interval.
 *
 * @author Roman Osipov
 */
public final class TimeInterval {

    //-------------------Fields---------------------------------------------------
    private final Date startDate;
    private final Date endDate;

    //-------------------Constructors---------------------------------------------
    public TimeInterval(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("startDate must be before endDate.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //-------------------Getters and setters--------------------------------------
    /**
     * Returns the start date of time interval.
     *
     * @return the start date of time interval.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of time interval.
     *
     * @return the end date of time interval.
     */
    public Date getEndDate() {
        return endDate;
    }

}
