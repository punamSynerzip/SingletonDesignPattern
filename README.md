# SingletonDesignPattern

Problem: 
	Suppose you to create a class for which only single instance or object should be created and that single object should be used by all other classes.

Solution: 
	Singleton design pattern is the best solution.
		
It has only one instance and provides global point of access to it.

Advantage: 
	Saves memory because object is not created at each request.

Usage: 	
	Mostly used in multithreaded applications
	Database applications for database connectivity
	Configuration settings
	Logging

Q. How to test that every instance returned by the singleton class is always same?
Ans. Check hash code of the returned instance. If it is,
Same → Singleton instance
Different → Both are different instances and there is something wrong with the    program logic.

How to create singleton design pattern?
Static member: It contains the instance of the Singleton class.
Private constructor: Prevent to instantiate this class from outside the class.
Static factory method: Provides the global point of access to singleton object.
 
There are two forms of singleton design pattern
Early Instantiation: creation of instance at load time.
Lazy Instantiation: creation of instance when required.
		

Example: Early loading 
public class SingletonDesignPattern {
 	/* private constructor */
private SingletonDesignPattern () {
 	}
 	/* pre-initialized instance of the singleton */
 	private static final  SingletonDesignPattern INSTANCE = new SingletonDesignPattern ();
 	/* Access point to the unique instance of the singleton */
 	public static SingletonDesignPattern getInstance() {
    	return INSTANCE;
 	}
}
Note:
Here, instance of the class is created at the time of classloading, Even if the instance is not needed.
To overcome this problem we can go for Lazy loading, which creates the instance when required.

Example: Lazy loading 
public class SingletonDesignPattern {
  	
private SingletonDesignPattern () {}
    	
	private static SingletonDesignPattern INSTANCE;

	public static SingletonDesignPattern getInstance() {
    		if (INSTANCE == null) {
        			INSTANCE = new SingletonDesignPattern ();
    		}
    	return INSTANCE;
	}
}


Note: 
Here, the problem arises in the multithreaded environment as, it can return more than one instance of singleton, 
if the two threads enter the if condition at the same time then two instance of singleton will be created, 
if the getInstance() method is not protected by synchronization.



 
Example: Synchronized singleton class solution
public class SingletonDesignPattern {
  	
private SingletonDesignPattern () {}
    	
	private static SingletonDesignPattern INSTANCE;

	public static SingletonDesignPattern getInstance() {
    		if (INSTANCE == null) {
synchronized(SingletonDesignPattern .class){
        		INSTANCE = new SingletonDesignPattern ();
    	}
}
return INSTANCE;
}
}


Note:
Here, Two threads can enter the if block at the same time when INSTANCE is null. 
The first thread enters the synchronized block  while the second thread is blocked.  
When the first thread gets out of the synchronized block, the second one, can then enter the synchronized block.
But when the second thread enters the synchronized block, it does not verify whether INSTANCE is null before initializing it.
Solution: 
Synchronize the getInstance()  method, but this would affect the performance of the program, if singleton is called frequently.
Double checked locking.
 
 
 
 
Example: Double checked locking
public class SingletonDesignPattern {
  	
private SingletonDesignPattern () {}
    	
	private static SingletonDesignPattern INSTANCE;

	public static SingletonDesignPattern getInstance() {
// double-checking lock
    		if (INSTANCE == null) {
// synchronized block
synchronized(SingletonDesignPattern .class){
if(INSTANCE == null){
INSTANCE = new SingletonDesignPattern ();
}
}
}
return INSTANCE;
}
}


Note: 
Here, This is the effective solution but it depends on the java compiler, which may vary in process of object creation.
As, allocation memory to object, execute constructor code, and assign memory reference to the field.
Which may return partial object.
Solution: using inner class


Example: Bill Pugh singleton implementation using inner class
public class SingletonDesignPattern {
    private SingletonDesignPattern (){}
   // static inner class which creates singleton instance
    private static class SingletonHelper{
    private static final SingletonDesignPattern INSTANCE = new SingletonDesignPattern ();
    }
    public static SingletonDesignPattern getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
When the SingletonDesignPattern class is loaded, SingletonHelper class is not loaded into memory and only when we call the getInstance() method, this class gets loaded and creates the SingletonDesignPattern class instance. 

So it may seem like eager initialization but it is lazy initialization and we don't even required to deal with complex thread-safety mechanism.


Factory Design Pattern:

Problem: When class doesn't know what subclasses required to create.

Solution: Factory design pattern is the best solution.

We just need to define an interface or abstract class for creating an object, let the subclasses decide which class to instantiate.



Advantage:
Can achieve loose coupling
Subclass can choose the type of object they want to create.




Abstract Factory Pattern:

Problem: When we want to develop a system having related or dependent objects. 

Solution: Abstract Factory design pattern is the best solution.

We just need to define an interface or an abstract class for creating families of related or dependent object without specifying their subclasses.

Advantage: Can easily exchange the related objects.








