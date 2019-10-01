class MyClass {
	MyClass(MyClass mc) { }
	  
	  int     foo1() { return 0; }
	  void    foo2(int value) { }
	  Object  foo4(int value) { return null; }
	  int     foo6(int value, String name) { return 0; }
	  int     foo7(int ... values) { return 0;}	
	  String  foo8(String value) {System.out.println(value);} // Noncompliant
}