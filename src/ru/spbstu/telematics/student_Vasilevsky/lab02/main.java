package ru.spbstu.telematics.student_Vasilevsky.lab02;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
//		tree.add(new Integer(1));
//		tree.add(new Integer(5));
//		tree.add(new Integer(2));
//		tree.add(new Integer(4));
//		tree.add(new Integer(4));
//		tree.add(new Integer(6));
//		tree.add(new Integer(7));
//		tree.add(new Integer(3));
		
		RedBlackTree<String> tree = new RedBlackTree<String>();
		tree.add("b");
		tree.add("d");
		tree.add("a");
		tree.add("c");
		
		for (String storedObject : tree) {
			System.out.println(storedObject);
		}
		
		System.out.println("Removing objects containing '4'");
		
//		tree.remove(new Integer(4));
//		for (Integer storedObject : tree) {
//			System.out.println(storedObject);
//		}	
//		System.out.println("done");
	}
}
