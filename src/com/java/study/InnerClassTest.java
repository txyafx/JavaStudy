package com.java.study;

/*
 * 对于方法的修饰词，子类方法要比父类的方法范围更加的宽泛。 父类为public，那么子类为private则出现错误。  之所以构造方法先运行父类再运行子类是因为构造方法是无法覆盖的。
 * 以下范围依次由严到宽： 
 * private ：本类访问； 
 * default ：表示默认，不仅本类访问，而且是同包可见。 
 * Protected：同包可见+不同包的子类可见 
 * Public ：表示所有的地方均可见。
 */
public class InnerClassTest {

	public static void main(String[] args) {
		new Circle(1.0);

		/*
		 * 成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象
		 */
		// 第一种方式：
		Outter outter = new Outter();
		Outter.Inner inner = outter.new Inner(); // 必须通过Outter对象来创建
		/*
		 * 内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限。比如上面的例子，
		 * 如果成员内部类Inner用private修饰
		 * ，则只能在外部类的内部访问，如果用public修饰，则任何地方都能访问；如果用protected修饰
		 * ，则只能在同一个包下或者继承外部类的情况下访问
		 * ；如果是默认访问权限，则只能在同一个包下访问。这一点和外部类有一点不一样，外部类只能被public和包访问两种权限修饰
		 * 。我个人是这么理解的，由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰。
		 */
		// Outter.Inner1 inner_1 = outter.new Inner1(); // 成员内部类遵循类成员修饰符的访问限定

		// 第二种方式：
		Outter.Inner inner1 = outter.getInnerInstance();

		/*
		 * 局部内部类
		 */
		Man man = new Man();
		man.getWoman().printSex();

		/*
		 * 匿名内部类
		 */
		TestAnonymousClass ac = new TestAnonymousClass();

		/*
		 * 静态内部类
		 */
		Outter1.Inner inner2 = new Outter1.Inner();
		
		
		WithInner wi = new WithInner();
        InheritInner obj = new InheritInner(wi);

	}

}

/*
 * ==============================================================================
 * 成员内部类
 * ========================================================================
 * ======
 */
class Circle {
	private double radius = 1;
	public static final int id = 100;

	public Circle() { // 每个类最好都定义无参数的构造函数

	}

	public Circle(double radius) {
		this.radius = radius;
		/*
		 * 虽然成员内部类可以无条件地访问外部类的成员，而外部类想访问成员内部类的成员却不是这么随心所欲了。在外部类中如果要访问成员内部类的成员，
		 * 必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问：
		 */
		this.getDrawInstance().drawSahpe(); // 必须先创建成员内部类的对象，再进行访问
	}

	private Draw getDrawInstance() {
		return new Draw();
	}

	// 成员内部类
	/*
	 * 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）
	 */
	class Draw { // 内部类
		private int radius = 2;

		// public static final int id=9; //成员内部类不适用静态方法和属性

		public Draw() {

		}

		public void drawSahpe() {
			System.out.println("drawshape");
			System.out.println(radius); // com.java.study.Circle.Draw.radius
			System.out.println(Circle.this.radius);// com.java.study.Circle.radius
													// 外部类的private成员
			System.out.println(Circle.this.id);// com.java.study.Circle.id
			System.out.println(id);// com.java.study.Circle.id
		}
	}
}

class Outter {
	private Inner inner = null;

	public Outter() {

	}

	public Inner getInnerInstance() {
		if (inner == null)
			inner = new Inner();
		return inner;
	}

	class Inner {
		public Inner() {

		}
	}

	private class Inner1 {
		public Inner1() {

		}
	}
}

/*
 * ==============================================================================
 * 局部内部类
 * ========================================================================
 * ======
 */
class People1 {
	public String sex;

	public People1() {

	}

	public void printSex() {
		System.out.println(sex);
	}
}

class Man {
	public Man() {

	}

	public People1 getWoman() {
		/*
		 * 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的
		 * 外部也无法访问
		 */
		class Woman extends People1 { // 局部内部类
			int age = 0;

			public Woman() {
				this.sex = "femal";
			}

			public void printAge() {
				System.out.println(age);
			}
		}
		return new Woman();
	}
}

/*
 * ==============================================================================
 * 局部内部类
 * ========================================================================
 * ======
 */
class Out {
	public Out() {

	}

	public void show() {
		System.out.println("In class out.");
	}
}

class TestAnonymousClass {
	public TestAnonymousClass() {
		this.newshow();
	}

	private void newshow() {
		Out anony = new Out() {
			public void show() {
				System.out.println("In class TestAnonymousClass");
			}
		};
		anony.show();

	}
}

/*
 * ==============================================================================
 * 静态内部类
 * ========================================================================
 * ======
 */

/*
 * 静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static。静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，
 * 并且它不能使用外部类的非static成员变量或者方法
 * ，这点很好理解，因为在没有外部类的对象的情况下，可以创建静态内部类的对象，如果允许访问外部类的非static成员就会产生矛盾
 * ，因为外部类的非static成员必须依附于具体的对象。
 */
class Outter1 {
	public int a = 10;
	static int b = 5;

	public Outter1() {

	}

	static class Inner {
		public Inner() {
			// System.out.println(a); //不能使用外部类的非static成员变量或者方法
			System.out.println(b);

		}
	}
}

/*
 * 内部类的继承
 * 
 */
class WithInner {
    class Inner{
    	public Inner(){
    		System.out.println("new inner");
    	}
    }
}
class InheritInner extends WithInner.Inner {
      
    // InheritInner() 是不能通过编译的，一定要加上形参
    InheritInner(WithInner wi) {
        wi.super(); //必须有这句调用
    }
}
