# 单例设计模式|实现

单例模式是最简单的设计模式之一。有时，我们只需要有一个类的实例，例如由多个对象共享的单个数据库连接，因为为每个对象创建单独的数据库连接可能代价高昂。同样，应用程序中可以有一个配置管理器或错误管理器来处理所有问题，而不是创建多个管理器。

**定义：**
*单例模式是一种设计模式，它将类的实例化限制为一个对象。*
让我们看一下实现此类类的各种设计选项。如果您对静态类变量和访问修饰符有很好的处理，这应该不是一项艰巨的任务。

**方法 1：经典实现** 

```java

// Classical Java implementation of singleton
// design pattern
class Singleton
{
    private static Singleton obj;
 
    // private constructor to force use of
    // getInstance() to create Singleton object
    private Singleton() {}
 
    public static Singleton getInstance()
    {
        if (obj==null)
            obj = new Singleton();
        return obj;
    }
}
```

在这里，我们声明 getInstance（） static，以便我们可以在不实例化类的情况下调用它。第一次调用 getInstance（） 时，它会创建一个新的单例对象，之后它只返回相同的对象。请注意，在我们需要它并调用getInstance（）方法之前，不会创建Singleton obj。这称为惰性实例化。
上述方法的主要问题是它不是线程安全的。请考虑以下执行顺序。

![1661525113056](imgs/1661525113056.png)



此执行序列为单例创建两个对象。因此，此经典实现不是线程安全的。

**方法 2：使 getInstance（） 同步** 

```java
// Thread Synchronized Java implementation of
// singleton design pattern
class Singleton
{
    private static Singleton obj;
 
    private Singleton() {}
 
    // Only one thread can execute this at a time
    public static synchronized Singleton getInstance()
    {
        if (obj==null)
            obj = new Singleton();
        return obj;
    }
}
```

在这里使用synced可以确保一次只有一个线程可以执行getInstance（）。
这种方法的主要缺点是，每次在创建单例对象时使用同步是昂贵的，并且可能会降低程序的性能。但是，如果 getInstance（） 的性能对您的应用程序并不重要，则此方法提供了一个干净简单的解决方案。

**方法 3：预先实例化** 

```java
// Static initializer based Java implementation of
// singleton design pattern
class Singleton
{
    private static Singleton obj = new Singleton();
 
    private Singleton() {}
 
    public static Singleton getInstance()
    {
        return obj;
    }
}
```

在这里，我们在静态初始值设定项中创建了单例的实例。JVM 在装入类时执行静态初始值设定项，因此可以保证这是线程安全的。仅当单例类较轻且在整个程序执行过程中使用时，才使用此方法。

**方法4（最佳）：使用“**[**双重检查锁定**](https://en.wikipedia.org/wiki/Double-checked_locking)**”**

如果您仔细注意到，一旦创建了对象，同步就不再有用，因为现在obj不会为null，任何操作序列都将导致一致的结果。

因此，我们只会在 getInstance（） 上获取一次锁，当 obj 为 null 时。通过这种方式，我们只同步第一种方式，只是我们想要的。

```java

// Double Checked Locking based Java implementation of
// singleton design pattern
class Singleton
{
    private static volatile Singleton obj  = null;
 
    private Singleton() {}
 
    public static Singleton getInstance()
    {
        if (obj == null)
        {
            // To make thread safe
            synchronized (Singleton.class)
            {
                // check again as multiple threads
                // can reach above step
                if (obj==null)
                    obj = new Singleton();
            }
        }
        return obj;
    }
}
```

我们已经声明了 obj [volatile](https://www.geeksforgeeks.org/volatile-keyword-in-java/)，它确保多个线程在将 obj 变量初始化为 Singleton 实例时正确提供 obj 变量。此方法大大减少了每次调用同步方法的开销。

# Java 中的单例类



在面向对象的编程中，单例类是一次只能有一个对象（类的实例）的类。在第一次之后，如果我们尝试实例化 Singleton 类，则新变量也会指向创建的第一个实例。因此，无论我们通过任何实例对类内的任何变量进行何种修改，都会影响所创建的单个实例的变量，并且如果我们通过定义的该类类型的任何变量访问该变量，则该变量是可见的。



**在设计单例类时，请记住将类定义为单例类时的关键点：**

1. 将构造函数设为私有。
2. 编写一个具有此单例类的返回类型对象的静态方法。这里，惰性初始化的概念用于编写此静态方法。

### 单例类的目的

Singleton 类的主要用途是将对象创建次数限制为仅一个。这通常可确保对资源（例如套接字或数据库连接）进行访问控制。

使用单例类不会发生内存空间浪费，因为它会限制实例的创建。由于对象创建将仅进行一次，而不是在每次发出新请求时创建它。

我们可以根据需要重复使用此单个对象。这就是为什么多线程和数据库应用程序主要使用Java中的单例模式进行缓存，日志记录，线程池，配置设置等等的原因。

例如，我们有一个许可证，我们只有一个数据库连接，或者假设如果我们的JDBC驱动程序不允许我们进行多线程处理，那么Singleton类就会进入画面，并确保一次只有一个连接或单个线程可以访问该连接。

### **如何在Java中设计/创建单例类？**

要创建单例类，我们必须遵循以下步骤：

**1.** 确保该类只有一个实例存在。

**2.** 通过以下方式提供对该实例的全局访问

- 将类的所有构造函数声明为私有。
- 提供返回对实例的引用的静态方法。惰性初始化概念用于编写静态方法。
- 实例存储为私有静态变量。

单例类的示例是**运行时类、操作 Servlet、服务定位器**。私有构造函数和工厂方法也是单例类的一个示例。

### 普通类和单例类之间的区别

我们可以在实例化类的对象的过程中将单例类与通常的类区分开来。为了实例化一个普通类，我们使用一个java构造函数。另一方面，要实例化单例类，我们使用 getInstance（） 方法。

另一个区别是，普通类在应用程序的生命周期结束时消失，而单例类不会随着应用程序的完成而销毁。

#### 单例类模式的形式

有两种形式的单例设计模式，它们是：

- **早期实例化：**对象创建在加载时进行。
- **惰性实例化：**对象创建是按照要求完成的。

**实现：** 让我们简要介绍一下单例类与 Java 中的普通类有何不同。这里的区别在于实例化方面，对于普通类，我们使用构造函数，而对于单例类，我们使用[*getInstance（）方法*](https://www.geeksforgeeks.org/java-signature-getinstance-method-with-examples/)，我们将在示例1中查看，如下所述。通常，为了避免混淆，在定义此方法时，我们也可以使用类名作为方法名，该方法将在下面的示例 2 中描述如下。

```java
// Java program implementing Singleton class
// with using  getInstance() method
  
// Class 1
// Helper class
class Singleton {
    // Static variable reference of single_instance
    // of type Singleton
    private static Singleton single_instance = null;
  
    // Declaring a variable of type String
    public String s;
  
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Singleton()
    {
        s = "Hello I am a string part of Singleton class";
    }
  
    // Static method
    // Static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();
  
        return single_instance;
    }
}
  
// Class 2
// Main class
class GFG {
    // Main driver method
    public static void main(String args[])
    {
        // Instantiating Singleton class with variable x
        Singleton x = Singleton.getInstance();
  
        // Instantiating Singleton class with variable y
        Singleton y = Singleton.getInstance();
  
        // Instantiating Singleton class with variable z
        Singleton z = Singleton.getInstance();
  
        // Printing the hash code for above variable as
        // declared
        System.out.println("Hashcode of x is "
                           + x.hashCode());
        System.out.println("Hashcode of y is "
                           + y.hashCode());
        System.out.println("Hashcode of z is "
                           + z.hashCode());
  
        // Condition check
        if (x == y && y == z) {
  
            // Print statement
            System.out.println(
                "Three objects point to the same memory location on the heap i.e, to the same object");
        }
  
        else {
            // Print statement
            System.out.println(
                "Three objects DO NOT point to the same memory location on the heap");
        }
    }
}
```

**输出**

```
Hashcode of x is 558638686
Hashcode of y is 558638686
Hashcode of z is 558638686
Three objects point to the same memory location on the heap i.e, to the same object
```

**输出说明：**

![1661525997492](imgs/1661525997492.png)

在单例类中，当我们第一次调用 ***getInstance（） 方法***时，它会创建一个名为 single_instance 的类的对象，并将其返回到变量。由于single_instance是静态的，因此它将从 null 更改为某个对象。下次，如果我们尝试调用 getInstance（） 方法，因为 single_instance 不为 null，它将返回到变量，而不是再次实例化 Singleton 类。这部分是通过 if 条件完成的。
在主类中，我们通过调用静态*方法 getInstance（）* 来实例化具有 3 个对象 x， y， z 的单例类。但实际上，在创建对象 x 之后，变量 y 和 z 指向对象 x，如图所示。因此，如果我们更改对象 x 的变量，则当我们访问对象 y 和 z 的变量时，这一点就会反映出来。此外，如果我们更改对象 z 的变量，这将在访问对象 x 和 y 的变量时反映出来。
现在我们已经完成了示例 1 的所有方面并实现了相同的操作，现在我们将实现具有方法名称的 Singleton 类作为类名。

```java
// Java program implementing Singleton class
// with method name as that of class

// Class 1
// Helper class
class Singleton {
	// Static variable single_instance of type Singleton
	private static Singleton single_instance = null;

	// Declaring a variable of type String
	public String s;

	// Constructor of this class
	// Here private constructor is used to
	// restricted to this class itself
	private Singleton()
	{
		s = "Hello I am a string part of Singleton class";
	}

	// Method
	// Static method to create instance of Singleton class
	public static Singleton Singleton()
	{
		// To ensure only one instance is created
		if (single_instance == null) {
			single_instance = new Singleton();
		}
		return single_instance;
	}
}

// Class 2
// Main class
class GFG {
	// Main driver method
	public static void main(String args[])
	{
		// Instantiating Singleton class with variable x
		Singleton x = Singleton.Singleton();

		// Instantiating Singleton class with variable y
		Singleton y = Singleton.Singleton();

		// instantiating Singleton class with variable z
		Singleton z = Singleton.Singleton();

		// Now changing variable of instance x
		// via toUpperCase() method
		x.s = (x.s).toUpperCase();

		// Print and display commands
		System.out.println("String from x is " + x.s);
		System.out.println("String from y is " + y.s);
		System.out.println("String from z is " + z.s);
		System.out.println("\n");

		// Now again changing variable of instance x
		z.s = (z.s).toLowerCase();

		System.out.println("String from x is " + x.s);
		System.out.println("String from y is " + y.s);
		System.out.println("String from z is " + z.s);
	}
}

```
输出
```
String from x is HELLO I AM A STRING PART OF SINGLETON CLASS
String from y is HELLO I AM A STRING PART OF SINGLETON CLASS
String from z is HELLO I AM A STRING PART OF SINGLETON CLASS


String from x is hello i am a string part of singleton class
String from y is hello i am a string part of singleton class
String from z is hello i am a string part of singleton class
```

**输出说明：** 在单例类中，当我们第一次调用 Singleton（） 方法时，它会创建一个名为 single_instance 的类 Singleton 的对象，并将其返回到变量。由于single_instance是静态的，因此它将从 null 更改为某个对象。下次如果我们尝试调用 Singleton（） 方法，由于 single_instance 不为 null，因此它将返回到变量，而不是再次实例化 Singleton 类。





# 单例设计模式|介绍

Singleton是**Gang of Four设计模式的**一部分，它被归类为**创造设计**模式。在本文中，我们将更深入地研究单例模式的用法。就建模而言，它是最简单的设计模式之一，但另一方面，就使用复杂性而言，这是最具争议的模式之一。



单例模式是一种设计模式，它限制类实例化其多个对象。它只不过是定义类的一种方式。类的定义方式是，在项目群或项目的完整执行中只创建类的一个实例。它用于仅需要类的单个实例来控制整个执行过程中的操作的情况。在任何情况下，单例类都不应具有多个实例，并且不收取任何成本。单例类用于日志记录、驱动程序对象、缓存和线程池、数据库连接。

![1661526345637](imgs/1661526345637.png)



单例类的实现应具有以下属性：

1. **它应该只有一个实例：**这是通过从类中提供类的实例来完成的。应阻止外部类或子类创建实例。这是通过在java中将构造函数私有来完成的，这样任何类都无法访问构造函数，因此无法实例化它。
2. **实例应可全局访问：**单例类的实例应全局可访问，以便每个类都可以使用它。在 Java 中，它是通过公开实例的访问说明符来完成的。

```java
//A singleton class should have public visibility
//so that complete application can use
public class GFG {
	
//static instance of class globally accessible
	public static GFG instance = new GFG();
    private GFG() {
        // private constructor so that class
        //cannot be instantiated from outside
        //this class
    }
}

```

**单例的初始化类型**

1. 单例类可以通过两种方法实例化：

2. **早期初始化 ：**在此方法中，无论是否使用类，都会初始化该类。这种方法的主要优点是它的简单性。在装入类时启动类。它的缺点是，无论是否使用类，它总是被初始化。
3. **惰性初始化：**在此方法中，类 in 仅在需要时才初始化。它可以使您不必在不需要时实例化类。通常，当我们创建单例类时，会使用惰性初始化。

**单例类的示例**

1. **java.lang.Runtime ：**Java在其 lang 包中提供了一个类 Runtime，该类本质上是单例的。每个 Java 应用程序都有一个类 Runtime 实例，该实例允许应用程序与运行应用程序的环境进行交互。当前运行时可以从 getRuntime（） 方法获取。
   应用程序无法实例化此类，因此无法为此类创建多个对象。因此 Runtime 是一个单例类。

2. **java.awt.Desktop ：**Desktop 类允许 Java 应用程序启动在本机桌面上注册的关联应用程序来处理 URI 或文件。
   支持的操作包括：

   - 启动用户默认浏览器以显示指定的URI;
     使用可选的 mailto URI 启动用户默认邮件客户端;
   - 启动已注册的应用程序以打开、编辑或打印指定的文件。
   - 此类提供与这些操作相对应的方法。这些方法查找在当前平台上注册的关联应用程序，并启动它以处理 URI 或文件。如果没有关联的应用程序或关联的应用程序无法启动，则会引发异常。
   - 每个操作都是由 Desktop.Action 类表示的操作类型。

   此类也无法从应用程序实例化。因此，它也是一个单例类。

**单例类的应用**

有很多单例模式的应用程序，如缓存内存，数据库连接，驱动程序，日志记录。其中一些专业是：-

1. **硬件接口接入：**单例的使用取决于要求。单例类还用于防止类的并发访问。实际上，在需要外部硬件资源使用限制的情况下，可以使用单例，例如硬件打印机，其中打印后台处理程序可以设置为单例，以避免多个并发访问并创建死锁。
2. **记录：**单例类用于日志文件生成。日志文件由记录器类对象创建。假设在一个应用程序中，日志记录实用程序必须根据从用户接收的消息生成一个日志文件。如果有多个客户端应用程序使用此日志记录实用程序类，则它们可能会创建此类的多个实例，并且在并发访问同一记录器文件期间可能会导致问题。我们可以将记录器实用程序类用作单例，并提供全局参考点，以便每个用户都可以使用此实用程序，并且没有2个用户同时访问它。
3. **配置文件：**这是 Singleton 模式的另一个潜在候选者，因为它具有性能优势，因为它可以防止多个用户重复访问和读取配置文件或属性文件。它创建配置文件的单个实例，该实例可以通过多个调用并发访问，因为它将提供加载到内存中对象中的静态配置数据。应用程序仅第一次从配置文件读取，此后从第二次调用开始，客户端应用程序从内存中对象读取数据。
4. **缓存：**我们可以将缓存用作单例对象，因为它可以具有全局参考点，并且对于将来对缓存对象的所有调用，客户端应用程序将使用内存中对象。

**要点**

- 单例类只能有一个实例，并且该实例应该是全局可访问的。
- java.lang.Runtime 和 java.awt.Desktop 是 JVM 提供的 2 个单例类。
- 单例设计模式是一种创建性设计模式。
- 应阻止外部类创建单例类的实例。



# Java 单例设计模式实践与示例



在[之前](https://www.geeksforgeeks.org/singleton-design-pattern-introduction/)的文章中，我们详细讨论了单例设计模式和单例类[实现](https://www.geeksforgeeks.org/singleton-design-pattern/)。
在本文中，我们将了解如何创建单例类。阅读本文后，您将能够根据您的要求创建单例类，这很简单，没有瓶颈。
在Java中有很多方法可以做到这一点。所有这些方式在实现模式方面都有所不同，但最终，它们都实现了单个实例的相同最终结果。
 



1. **预先初始化：**这是创建单例类的最简单方法。在这里，类的对象是在 JVM 加载到内存中时创建的。这是通过直接分配实例的引用来完成的。
   当程序将始终使用此类的实例，或者创建实例的成本在资源和时间方面不太大时，可以使用它。

```java

// Java code to create singleton class by
// Eager Initialization
public class GFG
{
  // public instance initialized when loading the class
  private static final GFG instance = new GFG();
 
  private GFG()
  {
    // private constructor
  }
  public static GFG getInstance(){
        return instance;
    }
}
```

1. 优点：
   1. 实现起来非常简单。
   2. 可能导致资源浪费。因为总是创建类的实例，无论它是否是必需的。
   3. 如果不需要，则在创建实例时也会浪费 CPU 时间。
   4. 无法进行异常处理。
2. **使用静态块：**这也是 Eager 初始化的一个子部分。唯一的区别是在静态块中创建对象，以便我们可以访问其创建，例如异常处理。通过这种方式，在类装入时也创建了对象。
   当在创建具有预先初始化的对象时出现异常时，可以使用它。

```java

// Java code to create singleton class
// Using Static block
public class GFG
{
  // public instance
  public static GFG instance;
 
  private GFG()
  {
    // private constructor
  }
static
  {
    // static block to initialize instance
    instance = new GFG();
  }
}
```

1. 优点：
   1. 实现起来非常简单。
   2. 无需实现 getInstance（） 方法。可以直接访问实例。
   3. 异常可以在静态块中处理。
   4. 可能导致资源浪费。因为总是创建类的实例，无论它是否是必需的。
   5. 如果不需要，则在创建实例时也会浪费 CPU 时间。

**2 惰性初始化：**在此方法中，仅在需要时创建对象。这可以防止资源浪费。需要 getInstance（） 方法的实现来返回实例。有一个空检查，如果未创建对象，则创建，否则返回以前创建的对象。为了确保类不能以任何其他方式实例化，构造函数是 final。由于对象是在方法中创建的，因此它确保除非需要，否则不会创建对象。实例保持私有，因此没有人可以直接访问它。
它可以在单线程环境中使用，因为多个线程可以破坏单例属性，因为它们可以同时访问 get 实例方法并创建多个对象。

```java

//Java Code to create singleton class
// With Lazy initialization
public class GFG
{
  // private instance, so that it can be
  // accessed by only by getInstance() method
  private static GFG instance;
 
  private GFG()
  {
    // private constructor
  }
 
  //method to return instance of class
  public static GFG getInstance()
  {
    if (instance == null)
    {
      // if instance is null, initialize
      instance = new GFG();
    }
    return instance;
  }
}
```

1. 优点：
   1. 仅当需要时才创建对象。它可以克服资源和CPU时间的浪费。
   2. 在方法中也可以进行异常处理。
   3. 每次都必须检查 null 条件。
   4. 无法直接访问实例。
   5. 在多线程环境中，它可能会破坏单例属性。
2. **线程安全单例：**创建线程安全的单例，以便即使在多线程环境中也能维护单例属性。为了使单例类线程安全，getInstance（） 方法已同步，以便多个线程无法同时访问它。



```java

// Java program to create Thread Safe
// Singleton class
public class GFG
{
  // private instance, so that it can be
  // accessed by only by getInstance() method
  private static GFG instance;
 
  private GFG()
  {
    // private constructor
  }
 
 //synchronized method to control simultaneous access
  synchronized public static GFG getInstance()
  {
    if (instance == null)
    {
      // if instance is null, initialize
      instance = new GFG();
    }
    return instance;
  }
}
```

1. 优点：
   1. 延迟初始化是可能的。
   2. 它也是线程安全的。
   3. getInstance（） 方法是同步的，因此由于多个线程无法同时访问它，因此会导致性能降低。
2. **使用双重检查锁定的惰性初始化：**在这种机制中，我们克服了同步代码的开销问题。在此方法中，getInstance 不会同步，但创建实例的块会同步，因此必须等待最少数量的线程，这只是第一次。

```java

// Java code to explain double check locking
public class GFG
{
  // private instance, so that it can be
  // accessed by only by getInstance() method
  private static GFG instance;
 
  private GFG()
  {
    // private constructor
  }
 
  public static GFG getInstance()
  {
    if (instance == null)
    {
      //synchronized block to remove overhead
      synchronized (GFG.class)
      {
        if(instance==null)
        {
          // if instance is null, initialize
          instance = new GFG();
        }
       
      }
    }
    return instance;
  }
}
```

1. 优点：
   1. 延迟初始化是可能的。
   2. 它也是线程安全的。
   3. 由于关键字同步，性能开销会降低。
   4. 第一次，它可能会影响性能。

**Bill Pugh Singleton Implementation：**在Java5之前，内存模型存在很多问题，上述方法在多线程环境中的某些情况下会导致故障。因此，Bill Pugh提出了一个用于单例的内部静态类的概念。

```java

// Java code for Bill Pugh Singleton Implementation
public class GFG
{
 
  private GFG()
  {
    // private constructor
  }
 
  // Inner class to provide instance of class
  private static class BillPughSingleton
  {
    private static final GFG INSTANCE = new GFG();
  }
 
  public static GFG getInstance()
  {
    return BillPughSingleton.INSTANCE;
  }
}
```

1. 加载单例类时，不会加载内部类，因此在加载类时不会创建对象。仅当调用 getInstance（） 方法时，才会创建内部类。因此，它可能看起来像是急于初始化，但它是惰性初始化。
   这是使用最广泛的方法，因为它不使用同步。

**何时使用什么**

1. 预先初始化很容易实现，但它可能会导致资源和 CPU 时间浪费。仅当初始化类的资源成本较低或程序始终需要类的实例时，才使用它。
2. 通过在 Eager 初始化中使用静态块，我们可以提供异常处理，也可以控制实例。
3. 使用synced，我们也可以在多线程环境中创建单例类，但它会导致性能降低，因此我们可以使用双重检查锁定机制。
    
4. Bill Pugh 实现是单例类使用最广泛的方法。大多数开发人员更喜欢它，因为它的简单性和优势。

# 如何防止单例图案的反射、序列化和克隆？

先决条件：[单例模式](https://www.geeksforgeeks.org/singleton-design-pattern-introduction/)

在本文中，我们将看到哪些概念可以破坏类的单例属性以及如何避免它们。主要有 3 个概念可以破坏类的单例属性。让我们一一讨论一下。

1. **反射：**[反射可能导致破坏](https://www.geeksforgeeks.org/reflection-in-java/)单例类的单例属性，如以下示例所示：

```java
// Java code to explain effect of Reflection
// on Singleton property
  
import java.lang.reflect.Constructor;
  
// Singleton class
class Singleton 
{
    // public instance initialized when loading the class
    public static Singleton instance = new Singleton();
      
    private Singleton() 
    {
        // private constructor
    }
}
  
public class GFG 
{
  
    public static void main(String[] args)
    {
        Singleton instance1 = Singleton.instance;
        Singleton instance2 = null;
        try
        {
            Constructor[] constructors = 
                    Singleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) 
            {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (Singleton) constructor.newInstance();
                break;
            }
        }
      
        catch (Exception e) 
        {
            e.printStackTrace();
        }
          
    System.out.println("instance1.hashCode():- " 
                                      + instance1.hashCode());
    System.out.println("instance2.hashCode():- " 
                                      + instance2.hashCode());
    }
}
```

```
Output:-
instance1.hashCode():- 366712642
instance2.hashCode():- 1829164700
```

运行此类后，您将看到哈希代码不同，这意味着创建了同一类的 2 个对象，并且已销毁单例模式。

**克服反射问题：**为了克服反射引起的问题，使用了[枚举](https://www.geeksforgeeks.org/enum-in-java/)，因为java在内部确保枚举值仅实例化一次。由于java枚举是全局可访问的，因此它们可以用于单例。它唯一的缺点是它不灵活，即它不允许延迟初始化。

```java

//Java program for Enum type singleton
public enum Singleton 
{
  INSTANCE;
}
```

由于枚举没有任何构造函数，因此反射无法使用它。枚举有它们的默认构造函数，我们不能自己调用它们。 **JVM 在内部处理枚举构造函数的创建和调用。**由于枚举不会将其构造函数定义提供给程序，因此我们也不可能通过反射来访问它们。因此，在枚举的情况下，反射不能破坏单例属性。

**2 序列化：-** [序列化](https://www.geeksforgeeks.org/serialization-in-java/)还可能导致单例类的单例属性中断。序列化用于转换字节流的对象并保存在文件中或通过网络发送。假设您序列化单例类的对象。然后，如果反序列化该对象，它将创建一个新实例，从而中断单一实例模式。

```java
// Java code to explain effect of 
// Serialization on singleton classes
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
  
class Singleton implements Serializable 
{
    // public instance initialized when loading the class
    public static Singleton instance = new Singleton();
      
    private Singleton() 
    {
        // private constructor
    }
}
  
  
public class GFG 
{
  
    public static void main(String[] args) 
    {
        try
        {
            Singleton instance1 = Singleton.instance;
            ObjectOutput out
                = new ObjectOutputStream(new FileOutputStream("file.text"));
            out.writeObject(instance1);
            out.close();
      
            // deserialize from file to object
            ObjectInput in 
                = new ObjectInputStream(new FileInputStream("file.text"));
              
            Singleton instance2 = (Singleton) in.readObject();
            in.close();
      
            System.out.println("instance1 hashCode:- "
                                                 + instance1.hashCode());
            System.out.println("instance2 hashCode:- " 
                                                 + instance2.hashCode());
        } 
          
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
```

```
Output:- 
instance1 hashCode:- 1550089733
instance2 hashCode:- 865113938
```

如您所见，两个实例的哈希码是不同的，因此单例类有 2 个对象。因此，该类不再是单例。

**克服序列化问题：-**为了克服这个问题，我们必须实现readResolve（）方法。

```java
// Java code to remove the effect of 
// Serialization on singleton classes
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
  
class Singleton implements Serializable 
{
    // public instance initialized when loading the class
    public static Singleton instance = new Singleton();
      
    private Singleton() 
    {
        // private constructor
    }
      
    // implement readResolve method
    protected Object readResolve()
    {
        return instance;
    }
}
  
public class GFG 
{
  
    public static void main(String[] args) 
    {
        try
        {
            Singleton instance1 = Singleton.instance;
            ObjectOutput out 
                = new ObjectOutputStream(new FileOutputStream("file.text"));
            out.writeObject(instance1);
            out.close();
          
            // deserialize from file to object
            ObjectInput in 
                = new ObjectInputStream(new FileInputStream("file.text"));
            Singleton instance2 = (Singleton) in.readObject();
            in.close();
          
            System.out.println("instance1 hashCode:- "
                                           + instance1.hashCode());
            System.out.println("instance2 hashCode:- "
                                           + instance2.hashCode());
        } 
          
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
```

```java
Output:- 
instance1 hashCode:- 1550089733
instance2 hashCode:- 1550089733
```

上面的两个哈希码是相同的，因此不会创建其他实例。



**克隆：**[克隆](https://www.geeksforgeeks.org/clone-method-in-java-2/)是创建重复对象的概念。使用克隆，我们可以创建对象的副本。假设，我们创建一个单例对象的克隆，然后它将创建一个副本，该副本是单例类的两个实例，因此该类不再是单例。

```java
// Java code to explain cloning 
// issue with singleton
class SuperClass implements Cloneable
{
  int i = 10;
  
  @Override
  protected Object clone() throws CloneNotSupportedException 
  {
    return super.clone();
  }
}
  
// Singleton class
class Singleton extends SuperClass
{
  // public instance initialized when loading the class
  public static Singleton instance = new Singleton();
  
  private Singleton() 
  {
    // private constructor
  }
}
  
public class GFG
{
  public static void main(String[] args) throws CloneNotSupportedException 
  {
    Singleton instance1 = Singleton.instance;
    Singleton instance2 = (Singleton) instance1.clone();
    System.out.println("instance1 hashCode:- "
                           + instance1.hashCode());
    System.out.println("instance2 hashCode:- " 
                           + instance2.hashCode()); 
  }
}
```

```
Output :- 
instance1 hashCode:- 366712642
instance2 hashCode:- 1829164700
```

两个不同的哈希码意味着有2个不同的单例类对象。

**克服克隆问题：-**要解决此问题，请重写 clone（） 方法，并从克隆方法中引发异常，该方法为 CloneNotSupportedException。现在，每当用户尝试创建单例对象的克隆时，它将引发异常，因此我们的类仍然是单例。

```java

// Java code to explain overcome 
// cloning issue with singleton
class SuperClass implements Cloneable
{
  int i = 10;
  
  @Override
  protected Object clone() throws CloneNotSupportedException 
  {
    return super.clone();
  }
}
  
// Singleton class
class Singleton extends SuperClass
{
  // public instance initialized when loading the class
  public static Singleton instance = new Singleton();
  
  private Singleton() 
  {
    // private constructor
  }
  
  @Override
  protected Object clone() throws CloneNotSupportedException 
  {
    throw new CloneNotSupportedException();
  }
}
  
public class GFG
{
  public static void main(String[] args) throws CloneNotSupportedException 
  {
    Singleton instance1 = Singleton.instance;
    Singleton instance2 = (Singleton) instance1.clone();
    System.out.println("instance1 hashCode:- " 
                         + instance1.hashCode());
    System.out.println("instance2 hashCode:- " 
                         + instance2.hashCode()); 
  }
}
```

```java
Output:-
Exception in thread "main" java.lang.CloneNotSupportedException
    at GFG.Singleton.clone(GFG.java:29)
    at GFG.GFG.main(GFG.java:38)
```

现在我们已经停止了 user 来创建单例类的克隆。如果不想引发异常，还可以从 clone 方法返回相同的实例。

```java

// Java code to explain overcome 
// cloning issue with singleton
class SuperClass implements Cloneable
{
  int i = 10;
  
  @Override
  protected Object clone() throws CloneNotSupportedException 
  {
    return super.clone();
  }
}
  
// Singleton class
class Singleton extends SuperClass
{
  // public instance initialized when loading the class
  public static Singleton instance = new Singleton();
  
  private Singleton() 
  {
    // private constructor
  }
  
  @Override
  protected Object clone() throws CloneNotSupportedException 
  {
    return instance;
  }
}
  
public class GFG
{
  public static void main(String[] args) throws CloneNotSupportedException 
  {
    Singleton instance1 = Singleton.instance;
    Singleton instance2 = (Singleton) instance1.clone();
    System.out.println("instance1 hashCode:- " 
                           + instance1.hashCode());
    System.out.println("instance2 hashCode:- "
                           + instance2.hashCode()); 
  }
}
```

```
Output:-
instance1 hashCode:- 366712642
instance2 hashCode:- 366712642
```

现在，由于两个实例的哈希码相同，这意味着它们表示单个实例。



















































[单例设计模式|实现 - 极客极客 (geeksforgeeks.org)](https://www.geeksforgeeks.org/singleton-design-pattern/)

[Java 中的 Singleton Class - GeeksforGeeks](https://www.geeksforgeeks.org/singleton-class-java/)

[Java 单例设计模式实践与示例 - GeeksforGeeks](https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/)

[如何防止单例图案的反射、序列化和克隆？- 极客极客 (geeksforgeeks.org)](https://www.geeksforgeeks.org/prevent-singleton-pattern-reflection-serialization-cloning/)