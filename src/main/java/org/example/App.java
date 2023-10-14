package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Computer pc = new Computer("brand1","model1","2023","2025",true,2,1024);
        System.out.println(pc.isWorking());
    }
}
