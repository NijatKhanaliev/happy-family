package com.happyfamily.interfaces;

public interface Foulable {
   default void foul(){
       System.out.println("I need to cover it up");
   }
}
