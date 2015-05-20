package com.java.study;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * ͨ��һ�������������İ���������
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
	 *            �����Ķ���
	 * @param att
	 *            ����������
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
	 *            �����Ķ���
	 * @param att
	 *            ����������
	 * @param value
	 *            ���õ�ֵ
	 * @param type
	 *            ����������
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
	 * �޸������С
	 * */
	public static Object arrayInc(Object obj, int len) {
		Class<?> arr = obj.getClass().getComponentType();
		Object newArr = Array.newInstance(arr, len);
		int co = Array.getLength(obj);
		System.arraycopy(obj, 0, newArr, 0, co);
		return newArr;
	}

	/**
	 * ��ӡ
	 * */
	public static void print(Object obj) {
		Class<?> c = obj.getClass();
		if (!c.isArray()) {
			return;
		}
		System.out.println("���鳤��Ϊ�� " + Array.getLength(obj));
		for (int i = 0; i < Array.getLength(obj); i++) {
			System.out.print(Array.get(obj, i) + " ");
		}
	}

	public static void main(String[] args) {
		// ͨ��һ�������������İ���������
		System.out.println("ͨ��һ�������������İ���������");
		Demo demo = new Demo();
		System.out.println(demo.getClass().getName());
		System.out.println();

		// ������Ķ�����ʵ����Class��ʵ��
		// ʵ����Class�����
		System.out.println("ͨ��һ��ʵ����Class������������İ���������");
		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		try {
			// һ�㾡������������ʽ
			demo1 = Class.forName("com.java.study.Demo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		demo2 = new Demo().getClass();
		demo3 = Demo.class;

		System.out.println("������   " + demo1.getName());
		System.out.println("������   " + demo2.getName());
		System.out.println("������   " + demo3.getName());
		System.out.println();

		// ͨ��Classʵ����������Ķ���
		System.out.println("ͨ��Classʵ����������Ķ���");
		Class<?> demo4 = null;
		try {
			demo4 = Class.forName("com.java.study.Humman");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Humman human = null;
		try {
			human = (Humman) demo4.newInstance(); // ʹ��Classʵ����������Ķ����ʱ��һ��Ҫ�Լ������޲����Ĺ��캯��

		} catch (Exception e) {
			e.printStackTrace();
		}
		human.setName("nero");
		human.setAge(27);
		System.out.println(human);
		System.out.println();

		// ͨ��Class�����������еĹ��캯�� ��Ҳ����ͨ�����ַ�ʽͨ��Class����������Ķ���
		System.out.println("ͨ��Class�����������еĹ��캯�� ��Ҳ����ͨ�����ַ�ʽͨ��Class����������Ķ���");
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

		// ȡ��ȫ���Ĺ��캯��
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

		// ����һ����ʵ�ֵĽӿ�
		System.out.println("����һ����ʵ�ֵĽӿ�");
		Class<?> demo6 = null;
		try {
			demo6 = Class.forName("com.java.study.Mankind");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �������нӿ�
		Class<?> intes[] = demo6.getInterfaces();
		for (int i = 0; i < intes.length; i++) {
			System.out.println("ʵ�ֵĽӿ�   " + intes[i].getName());
		}
		System.out.println();

		Class<?> demo7 = null;
		try {
			demo7 = Class.forName("com.java.study.Mankind");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ȡ���������еĸ���
		System.out.println("ȡ���������еĸ���");
		// ȡ�ø���
		Class<?> temp = demo7.getSuperclass();
		System.out.println("�̳еĸ���Ϊ��   " + temp.getName());
		System.out.println();

		// ����������е�ȫ�����캯��
		System.out.println("����������е�ȫ�����캯��");
		Constructor<?> consts[] = demo7.getConstructors();
		for (int i = 0; i < consts.length; i++) {
			System.out.println("���췽����  " + consts[i]);
		}
		System.out.println();

		// ����������е�ȫ�����캯�������η�
		System.out.println("����������е�ȫ�����캯�������η�");
		for (int i = 0; i < consts.length; i++) {
			Class<?> p[] = consts[i].getParameterTypes();
			System.out.print("���췽����  ");
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

		// ����������еķ���
		System.out.println("����������еķ���");
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

		System.out.println("===============��������========================");
		// ȡ�ñ����ȫ������
		Field[] field = demo7.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			// Ȩ�����η�
			int mo = field[i].getModifiers();
			String priv = Modifier.toString(mo);
			// ��������
			Class<?> type = field[i].getType();
			System.out.println(priv + " " + type.getName() + " "
					+ field[i].getName() + ";");
		}
		System.out
				.println("===============ʵ�ֵĽӿڻ��߸��������========================");
		// ȡ��ʵ�ֵĽӿڻ��߸��������
		Field[] filed1 = demo7.getFields();
		for (int j = 0; j < filed1.length; j++) {
			// Ȩ�����η�
			int mo = filed1[j].getModifiers();
			String priv = Modifier.toString(mo);
			// ��������
			Class<?> type = filed1[j].getType();
			System.out.println(priv + " " + type.getName() + " "
					+ filed1[j].getName() + ";");
		}
		System.out.println();
		try {
			// ����Person���е�sayChina����
			Method method1 = demo7.getMethod("sayChina");
			method1.invoke(demo7.newInstance());
			// ����Person��sayHello����
			method1 = demo7.getMethod("sayHello", String.class, int.class);
			method1.invoke(demo7.newInstance(), "Rollen", 20);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

		// �����������set��get����
		System.out.println("�����������set��get����");
		Object obj = null;
		try {
			obj = demo7.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setter(obj, "Sex", "��", String.class);
		getter(obj, "Sex");
		System.out.println();

		// ͨ�������������
		System.out.println("ͨ�������������");
		Object obj1 = null;
		try {
			obj1 = demo7.newInstance();
			Field field1 = demo7.getDeclaredField("sex");
			field1.setAccessible(true);
			field1.set(obj, "Ů");
			System.out.println(field1.get(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

		// ͨ������ȡ�ò��޸��������Ϣ��
		System.out.println("ͨ������ȡ�ò��޸��������Ϣ");
		int[] temp1 = { 1, 2, 3, 4, 5 };
		Class<?> demo8 = temp1.getClass().getComponentType();
		System.out.println("�������ͣ� " + demo8.getName());
		System.out.println("���鳤��  " + Array.getLength(temp1));
		System.out.println("����ĵ�һ��Ԫ��: " + Array.get(temp1, 0));
		Array.set(temp1, 0, 100);
		System.out.println("�޸�֮�������һ��Ԫ��Ϊ�� " + Array.get(temp1, 0));
		System.out.println();

		// ͨ�������޸������С
		System.out.println("ͨ�������޸������С");
		int[] array1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] array2 = (int[]) arrayInc(array1, 15);
		print(array2);
		System.out.println("\n=====================");
		String[] atr1 = { "a", "b", "c" };
		String[] atr2 = (String[]) arrayInc(atr1, 15);
		print(atr2);
		System.out.println();

		// ����������
		System.out.println("����������");
		Demo d = new Demo();
		System.out.println("�������  "
				+ d.getClass().getClassLoader().getClass().getName());
		System.out.println();

		// ��ɶ�̬����
		/*
		 * �����������
		 * 
		 * ��һ����������֮����һ������Ҫ��ʼʹ���࣬���Ҫʹ��һ���࣬�϶��벻��JVM���ڳ���ִ����JVMͨ��װ�أ����ӣ���ʼ����3��������ɡ�
		 * 
		 * ���װ����ͨ�����������ɵģ���������.class�ļ��Ķ������ļ�װ��JVM�ķ������������ڶ�����������������java.lang.Class����
		 * ��������װ���ݡ� ����ͬһ����ֻ�ᱻ��װ����װ����ǰ
		 * 
		 * ���Ӿ��ǰѶ�����������װΪ�������е�״̬��
		 * 
		 * 
		 * 
		 * ���ӷ�ΪУ�飬׼����������3���׶�
		 * 
		 * У��һ������ȷ�ϴ˶������ļ��Ƿ��ʺϵ�ǰ��JVM���汾����
		 * 
		 * ׼������Ϊ��̬��Ա�����ڴ�ռ䣬��������Ĭ��ֵ
		 * 
		 * ����ָ����ת���������еĴ�����Ϊֱ�����õĹ��̣�ֱ�����еķ������ö����Ա����г���ʹ�ã����������Ķ�Ӧ��ϵ��
		 * 
		 * ���֮������Ҳ������˳�ʼ������ʼ��֮����Ķ���Ϳ�������ʹ���ˣ�ֱ��һ��������ʹ��֮�󣬽����������ա��ͷſռ䡣
		 * 
		 * ��û���κ�����ָ��Class����ʱ�ͻᱻж�أ����������������
		 */
		System.out.println("��̬����");
		MyInvocationHandler demo9 = new MyInvocationHandler();
		Subject sub = (Subject) demo9.bind(new RealSubject());
		String info = sub.say("Rollen", 20);
		System.out.println(info);
		System.out.println();

	}
}

// ������Ŀ�ӿ�
interface Subject {
	public String say(String name, int age);
}

// ������ʵ��Ŀ
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
