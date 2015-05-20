package com.java.study;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * 通过一个对象获得完整的包名和类名
 * */
class Demo {

}

class Humman {

	public Humman() {

	}

	public Humman(String name) {
		this.name = name;
	}

	public Humman(int age) {
		this.age = age;
	}

	public Humman(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Humman [name=" + name + ", age=" + age + "]";
	}

	private String name;
	private int age;

}

interface China {
	public static final String name = "nero";
	public static int age = 27;

	public void sayChina();

	public void sayHello(String name, int age);

}

class Mankind implements China {

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Mankind() {

	}

	public Mankind(String sex) {
		this.sex = sex;

	}

	@Override
	public void sayChina() {
		System.out.println("hello, china.");
	}

	@Override
	public void sayHello(String name, int age) {
		System.out.println(name + " " + age);
	}

	private String sex;

}

public class ReflectDemo {
	/**
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * */
	public static void getter(Object obj, String att) {
		try {
			Method method = obj.getClass().getMethod("get" + att);
			System.out.println(method.invoke(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * @param value
	 *            设置的值
	 * @param type
	 *            参数的属性
	 * */
	public static void setter(Object obj, String att, Object value,
			Class<?> type) {
		try {
			Method method = obj.getClass().getMethod("set" + att, type);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改数组大小
	 * */
	public static Object arrayInc(Object obj, int len) {
		Class<?> arr = obj.getClass().getComponentType();
		Object newArr = Array.newInstance(arr, len);
		int co = Array.getLength(obj);
		System.arraycopy(obj, 0, newArr, 0, co);
		return newArr;
	}

	/**
	 * 打印
	 * */
	public static void print(Object obj) {
		Class<?> c = obj.getClass();
		if (!c.isArray()) {
			return;
		}
		System.out.println("数组长度为： " + Array.getLength(obj));
		for (int i = 0; i < Array.getLength(obj); i++) {
			System.out.print(Array.get(obj, i) + " ");
		}
	}

	public static void main(String[] args) {
		// 通过一个对象获得完整的包名和类名
		System.out.println("通过一个对象获得完整的包名和类名");
		Demo demo = new Demo();
		System.out.println(demo.getClass().getName());
		System.out.println();

		// 所有类的对象其实都是Class的实例
		// 实例化Class类对象
		System.out.println("通过一个实例化Class类对象获得完整的包名和类名");
		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		try {
			// 一般尽量采用这种形式
			demo1 = Class.forName("com.java.study.Demo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		demo2 = new Demo().getClass();
		demo3 = Demo.class;

		System.out.println("类名称   " + demo1.getName());
		System.out.println("类名称   " + demo2.getName());
		System.out.println("类名称   " + demo3.getName());
		System.out.println();

		// 通过Class实例化其他类的对象
		System.out.println("通过Class实例化其他类的对象");
		Class<?> demo4 = null;
		try {
			demo4 = Class.forName("com.java.study.Humman");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Humman human = null;
		try {
			human = (Humman) demo4.newInstance(); // 使用Class实例化其他类的对象的时候，一定要自己定义无参数的构造函数

		} catch (Exception e) {
			e.printStackTrace();
		}
		human.setName("nero");
		human.setAge(27);
		System.out.println(human);
		System.out.println();

		// 通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
		System.out.println("通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）");
		Class<?> demo5 = null;
		try {
			demo5 = Class.forName("com.java.study.Humman");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Humman human1 = null;
		Humman human2 = null;
		Humman human3 = null;
		Humman human4 = null;

		// 取得全部的构造函数
		Constructor<?> cons[] = demo5.getConstructors();
		System.out.println(cons[0]);
		System.out.println(cons[1]);
		System.out.println(cons[2]);
		System.out.println(cons[3]);
		try {

			human1 = (Humman) cons[0].newInstance("nero", 27);
			human2 = (Humman) cons[1].newInstance(27);
			human3 = (Humman) cons[2].newInstance("nero");
			human4 = (Humman) cons[3].newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(human1);
		System.out.println(human2);
		System.out.println(human3);
		System.out.println(human4);
		System.out.println();

		// 返回一个类实现的接口
		System.out.println("返回一个类实现的接口");
		Class<?> demo6 = null;
		try {
			demo6 = Class.forName("com.java.study.Mankind");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 保存所有接口
		Class<?> intes[] = demo6.getInterfaces();
		for (int i = 0; i < intes.length; i++) {
			System.out.println("实现的接口   " + intes[i].getName());
		}
		System.out.println();

		Class<?> demo7 = null;
		try {
			demo7 = Class.forName("com.java.study.Mankind");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取得其他类中的父类
		System.out.println("取得其他类中的父类");
		// 取得父类
		Class<?> temp = demo7.getSuperclass();
		System.out.println("继承的父类为：   " + temp.getName());
		System.out.println();

		// 获得其他类中的全部构造函数
		System.out.println("获得其他类中的全部构造函数");
		Constructor<?> consts[] = demo7.getConstructors();
		for (int i = 0; i < consts.length; i++) {
			System.out.println("构造方法：  " + consts[i]);
		}
		System.out.println();

		// 获得其他类中的全部构造函数的修饰符
		System.out.println("获得其他类中的全部构造函数的修饰符");
		for (int i = 0; i < consts.length; i++) {
			Class<?> p[] = consts[i].getParameterTypes();
			System.out.print("构造方法：  ");
			int mo = consts[i].getModifiers();
			System.out.print(Modifier.toString(mo) + " ");
			System.out.print(consts[i].getName());
			System.out.print("(");
			for (int j = 0; j < p.length; ++j) {
				System.out.print(p[j].getName() + " arg" + i);
				if (j < p.length - 1) {
					System.out.print(",");
				}
			}
			System.out.println("){}");
		}
		System.out.println();

		// 获得其他类中的方法
		System.out.println("获得其他类中的方法");
		Method method[] = demo7.getMethods();
		for (int i = 0; i < method.length; ++i) {
			Class<?> returnType = method[i].getReturnType();
			Class<?> para[] = method[i].getParameterTypes();
			int temp1 = method[i].getModifiers();
			System.out.print(Modifier.toString(temp1) + " ");
			System.out.print(returnType.getName() + "  ");
			System.out.print(method[i].getName() + " ");
			System.out.print("(");
			for (int j = 0; j < para.length; ++j) {
				System.out.print(para[j].getName() + " " + "arg" + j);
				if (j < para.length - 1) {
					System.out.print(",");
				}
			}
			Class<?> exce[] = method[i].getExceptionTypes();
			if (exce.length > 0) {
				System.out.print(") throws ");
				for (int k = 0; k < exce.length; ++k) {
					System.out.print(exce[k].getName() + " ");
					if (k < exce.length - 1) {
						System.out.print(",");
					}
				}
			} else {
				System.out.print(")");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("===============本类属性========================");
		// 取得本类的全部属性
		Field[] field = demo7.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			// 权限修饰符
			int mo = field[i].getModifiers();
			String priv = Modifier.toString(mo);
			// 属性类型
			Class<?> type = field[i].getType();
			System.out.println(priv + " " + type.getName() + " "
					+ field[i].getName() + ";");
		}
		System.out
				.println("===============实现的接口或者父类的属性========================");
		// 取得实现的接口或者父类的属性
		Field[] filed1 = demo7.getFields();
		for (int j = 0; j < filed1.length; j++) {
			// 权限修饰符
			int mo = filed1[j].getModifiers();
			String priv = Modifier.toString(mo);
			// 属性类型
			Class<?> type = filed1[j].getType();
			System.out.println(priv + " " + type.getName() + " "
					+ filed1[j].getName() + ";");
		}
		System.out.println();
		try {
			// 调用Person类中的sayChina方法
			Method method1 = demo7.getMethod("sayChina");
			method1.invoke(demo7.newInstance());
			// 调用Person的sayHello方法
			method1 = demo7.getMethod("sayHello", String.class, int.class);
			method1.invoke(demo7.newInstance(), "Rollen", 20);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

		// 调用其他类的set和get方法
		System.out.println("调用其他类的set和get方法");
		Object obj = null;
		try {
			obj = demo7.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setter(obj, "Sex", "男", String.class);
		getter(obj, "Sex");
		System.out.println();

		// 通过反射操作属性
		System.out.println("通过反射操作属性");
		Object obj1 = null;
		try {
			obj1 = demo7.newInstance();
			Field field1 = demo7.getDeclaredField("sex");
			field1.setAccessible(true);
			field1.set(obj, "女");
			System.out.println(field1.get(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

		// 通过反射取得并修改数组的信息：
		System.out.println("通过反射取得并修改数组的信息");
		int[] temp1 = { 1, 2, 3, 4, 5 };
		Class<?> demo8 = temp1.getClass().getComponentType();
		System.out.println("数组类型： " + demo8.getName());
		System.out.println("数组长度  " + Array.getLength(temp1));
		System.out.println("数组的第一个元素: " + Array.get(temp1, 0));
		Array.set(temp1, 0, 100);
		System.out.println("修改之后数组第一个元素为： " + Array.get(temp1, 0));
		System.out.println();

		// 通过反射修改数组大小
		System.out.println("通过反射修改数组大小");
		int[] array1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] array2 = (int[]) arrayInc(array1, 15);
		print(array2);
		System.out.println("\n=====================");
		String[] atr1 = { "a", "b", "c" };
		String[] atr2 = (String[]) arrayInc(atr1, 15);
		print(atr2);
		System.out.println();

		// 获得类加载器
		System.out.println("获得类加载器");
		Demo d = new Demo();
		System.out.println("类加载器  "
				+ d.getClass().getClassLoader().getClass().getName());
		System.out.println();

		// 完成动态代理
		/*
		 * 类的生命周期
		 * 
		 * 在一个类编译完成之后，下一步就需要开始使用类，如果要使用一个类，肯定离不开JVM。在程序执行中JVM通过装载，链接，初始化这3个步骤完成。
		 * 
		 * 类的装载是通过类加载器完成的，加载器将.class文件的二进制文件装入JVM的方法区，并且在堆区创建描述这个类的java.lang.Class对象
		 * 。用来封装数据。 但是同一个类只会被类装载器装载以前
		 * 
		 * 链接就是把二进制数据组装为可以运行的状态。
		 * 
		 * 
		 * 
		 * 链接分为校验，准备，解析这3个阶段
		 * 
		 * 校验一般用来确认此二进制文件是否适合当前的JVM（版本），
		 * 
		 * 准备就是为静态成员分配内存空间，。并设置默认值
		 * 
		 * 解析指的是转换常量池中的代码作为直接引用的过程，直到所有的符号引用都可以被运行程序使用（建立完整的对应关系）
		 * 
		 * 完成之后，类型也就完成了初始化，初始化之后类的对象就可以正常使用了，直到一个对象不再使用之后，将被垃圾回收。释放空间。
		 * 
		 * 当没有任何引用指向Class对象时就会被卸载，结束类的生命周期
		 */
		System.out.println("动态代理");
		MyInvocationHandler demo9 = new MyInvocationHandler();
		Subject sub = (Subject) demo9.bind(new RealSubject());
		String info = sub.say("Rollen", 20);
		System.out.println(info);
		System.out.println();

	}
}

// 定义项目接口
interface Subject {
	public String say(String name, int age);
}

// 定义真实项目
class RealSubject implements Subject {
	@Override
	public String say(String name, int age) {
		return name + "  " + age;
	}
}

class MyInvocationHandler implements InvocationHandler {
	private Object obj = null;

	public Object bind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object temp = method.invoke(this.obj, args);
		return temp;
	}
}
