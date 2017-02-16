package com.cifpay.insurance;

public class GenericTest {

	public static void main(String[] args) {

		Box<String> name = new Box<String>();
		name.setData("data");
		System.out.println("name:" + name.getData());
	}

}

	class Box<T> {
	
		private T data;
	
		public Box() {
	
		}
	
		public Box(T data) {
			this.data = data;
		}
	
		public T getData() {
			return data;
		}
	
		public void setData(T data) {
			this.data = data;
		}
	
	}