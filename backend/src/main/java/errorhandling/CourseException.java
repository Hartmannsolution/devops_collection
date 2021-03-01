/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author root
 */
public class CourseException extends Exception{
    
    public CourseException(String message) {
        super(message);
    }

    public CourseException() {
        super("Something went wrong");
    }
    
}
