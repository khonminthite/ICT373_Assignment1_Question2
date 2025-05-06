/**
 * @Title: ICT 373 A1
 * @Author: Khon Min Thite
 * @Date: 2/10/2024
 * @File: Supplements.java
 * @Purpose: A class that represents a supplement
 * A supplement has a name and a weekly cost
 * @Assumptions:
 * @Limitations:
 */
package magazine;

public class Supplement {
    /**
     * A supplement has a name and a weekly cost
     */
    private String name;
    private double weeklyCost;

    /**
     * Constructor
     * 
     * @param name The name of the supplement
     * @param weeklyCost The weekly cost of the supplement
     */
    public Supplement(String name, double weeklyCost) {
        this.name = name;
        this.weeklyCost = weeklyCost;
    }

    /**
     * Getter for name
     * 
     * @return The name of the supplement
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for weekly cost
     * 
     * @return The weekly cost of the supplement
     */
    public double getWeeklyCost() {
        return weeklyCost;
    }

    /**
     * Setter for name
     * 
     * @param name The name of the supplement
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for weekly cost
     * 
     * @param weeklyCost The weekly cost of the supplement
     */
    public void setWeeklyCost(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }
}