package edu.gatech.oad.antlab.person;
import java.util.Stack;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Eric Phan
 * @version 1.0
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
    private String calc(String input) {
        Stack calc = new Stack();
        String random = "";
        for(int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if(temp >= 65 & temp < 88 || temp >= 97 && temp < 120) {
                calc.push((char) (input.charAt(i) + 3));
            }
            else {
                calc.push(((input.charAt(i)) * 6 / 9) - 20);
            }
        }
        for(int z = 0; z <=  calc.size(); z++) {
            random = random + calc.pop();
        }
        return random;
    }
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
