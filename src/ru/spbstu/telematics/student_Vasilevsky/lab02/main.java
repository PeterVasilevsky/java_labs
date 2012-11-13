package ru.spbstu.telematics.student_Vasilevsky.lab02;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		tree.add(new Integer(1));
		tree.add(new Integer(5));
		tree.add(new Integer(2));
		tree.add(new Integer(1));
		tree.add(new Integer(4));
		tree.add(new Integer(6));
		tree.add(new Integer(7));
		tree.add(new Integer(3));
		
//		tree.deleteNode(tree.getRoot().getRight());
//		tree.deleteNode(tree.getRoot().getLeft());
//		tree.deleteNode(tree.getRoot().getLeft());
		tree.deleteNode(tree.getRoot());
		
//		tree.remove(new Integer(3));
		
		System.out.println("done");
	}

}
