package com.osrapi.controllers.entities;

/**
 * @author drau
 */
public final class TreasureRoll {
    /** the die roll. */
    private long roll;
    /** the wealth code. */
    private String wealth;
    /** Creates a new instance of {@link TreasureRoll}. */
    public TreasureRoll() {
        super();
    }
    /**
     * Creates a new instance of {@link TreasureRoll}.
     * @param r the die roll
     * @param w the wealth code
     */
    public TreasureRoll(final long r, final String w) {
        roll = r;
        wealth = w;
    }
    /**
     * Gets the die roll.
     * @return {@link long}
     */
    public long getRoll() {
        return roll;
    }
    /**
     * Gets the wealth code.
     * @return {@link String}
     */
    public String getWealth() {
        return wealth;
    }
    /**
     * Sets the die roll.
     * @param val the value to set
     */
    public void setRoll(final long val) {
        roll = val;
    }
    /**
     * Sets the wealth code.
     * @param val the value to set
     */
    public void setWealth(final String val) {
        wealth = val;
    }
}
