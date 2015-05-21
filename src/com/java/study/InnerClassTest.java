package com.java.study;

/*
 * ���ڷ��������δʣ����෽��Ҫ�ȸ���ķ�����Χ���ӵĿ��� ����Ϊpublic����ô����Ϊprivate����ִ���  ֮���Թ��췽�������и�����������������Ϊ���췽�����޷����ǵġ�
 * ���·�Χ�������ϵ��� 
 * private ��������ʣ� 
 * default ����ʾĬ�ϣ�����������ʣ�������ͬ���ɼ��� 
 * Protected��ͬ���ɼ�+��ͬ��������ɼ� 
 * Public ����ʾ���еĵط����ɼ���
 */
public class InnerClassTest {

	public static void main(String[] args) {
		new Circle(1.0);

		/*
		 * ��Ա�ڲ����������ⲿ������ڵģ�Ҳ����˵�����Ҫ������Ա�ڲ���Ķ���ǰ���Ǳ������һ���ⲿ��Ķ���
		 */
		// ��һ�ַ�ʽ��
		Outter outter = new Outter();
		Outter.Inner inner = outter.new Inner(); // ����ͨ��Outter����������
		/*
		 * �ڲ������ӵ��private����Ȩ�ޡ�protected����Ȩ�ޡ�public����Ȩ�޼�������Ȩ�ޡ�������������ӣ�
		 * �����Ա�ڲ���Inner��private����
		 * ����ֻ�����ⲿ����ڲ����ʣ������public���Σ����κεط����ܷ��ʣ������protected����
		 * ����ֻ����ͬһ�����»��߼̳��ⲿ�������·���
		 * �������Ĭ�Ϸ���Ȩ�ޣ���ֻ����ͬһ�����·��ʡ���һ����ⲿ����һ�㲻һ�����ⲿ��ֻ�ܱ�public�Ͱ���������Ȩ������
		 * ���Ҹ�������ô���ģ����ڳ�Ա�ڲ��࿴���������ⲿ���һ����Ա�����Կ�������ĳ�Աһ��ӵ�ж���Ȩ�����Ρ�
		 */
		// Outter.Inner1 inner_1 = outter.new Inner1(); // ��Ա�ڲ�����ѭ���Ա���η��ķ����޶�

		// �ڶ��ַ�ʽ��
		Outter.Inner inner1 = outter.getInnerInstance();

		/*
		 * �ֲ��ڲ���
		 */
		Man man = new Man();
		man.getWoman().printSex();

		/*
		 * �����ڲ���
		 */
		TestAnonymousClass ac = new TestAnonymousClass();

		/*
		 * ��̬�ڲ���
		 */
		Outter1.Inner inner2 = new Outter1.Inner();
		
		
		WithInner wi = new WithInner();
        InheritInner obj = new InheritInner(wi);

	}

}

/*
 * ==============================================================================
 * ��Ա�ڲ���
 * ========================================================================
 * ======
 */
class Circle {
	private double radius = 1;
	public static final int id = 100;

	public Circle() { // ÿ������ö������޲����Ĺ��캯��

	}

	public Circle(double radius) {
		this.radius = radius;
		/*
		 * ��Ȼ��Ա�ڲ�������������ط����ⲿ��ĳ�Ա�����ⲿ������ʳ�Ա�ڲ���ĳ�Աȴ������ô���������ˡ����ⲿ�������Ҫ���ʳ�Ա�ڲ���ĳ�Ա��
		 * �����ȴ���һ����Ա�ڲ���Ķ�����ͨ��ָ�������������������ʣ�
		 */
		this.getDrawInstance().drawSahpe(); // �����ȴ�����Ա�ڲ���Ķ����ٽ��з���
	}

	private Draw getDrawInstance() {
		return new Draw();
	}

	// ��Ա�ڲ���
	/*
	 * ��Ա�ڲ�����������������ⲿ������г�Ա���Ժͳ�Ա����������private��Ա�;�̬��Ա��
	 */
	class Draw { // �ڲ���
		private int radius = 2;

		// public static final int id=9; //��Ա�ڲ��಻���þ�̬����������

		public Draw() {

		}

		public void drawSahpe() {
			System.out.println("drawshape");
			System.out.println(radius); // com.java.study.Circle.Draw.radius
			System.out.println(Circle.this.radius);// com.java.study.Circle.radius
													// �ⲿ���private��Ա
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
 * �ֲ��ڲ���
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
		 * �ֲ��ڲ�������Ƿ��������һ���ֲ�����һ�����ǲ�����public��protected��private�Լ�static���η���
		 * �ⲿҲ�޷�����
		 */
		class Woman extends People1 { // �ֲ��ڲ���
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
 * �ֲ��ڲ���
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
 * ��̬�ڲ���
 * ========================================================================
 * ======
 */

/*
 * ��̬�ڲ���Ҳ�Ƕ�������һ����������ֻ࣬���������ǰ�����һ���ؼ���static����̬�ڲ����ǲ���Ҫ�������ⲿ��ģ�������ľ�̬��Ա�����е����ƣ�
 * ����������ʹ���ⲿ��ķ�static��Ա�������߷���
 * �����ܺ���⣬��Ϊ��û���ⲿ��Ķ��������£����Դ�����̬�ڲ���Ķ��������������ⲿ��ķ�static��Ա�ͻ����ì��
 * ����Ϊ�ⲿ��ķ�static��Ա���������ھ���Ķ���
 */
class Outter1 {
	public int a = 10;
	static int b = 5;

	public Outter1() {

	}

	static class Inner {
		public Inner() {
			// System.out.println(a); //����ʹ���ⲿ��ķ�static��Ա�������߷���
			System.out.println(b);

		}
	}
}

/*
 * �ڲ���ļ̳�
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
      
    // InheritInner() �ǲ���ͨ������ģ�һ��Ҫ�����β�
    InheritInner(WithInner wi) {
        wi.super(); //������������
    }
}
