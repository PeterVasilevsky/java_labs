package ru.spbstu.telematics.student_Vasilevsky.lab02;

public class Node {
	private Node parent;
	private Node left;
	private Node right;
	private boolean isRed;
	private Comparable storedObject;
	private boolean isNull;
	
	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public void printInfo() {
		if (this.isRed == true) {
			System.out.println("color = red");
		}
		else {
			System.out.println("color = black");
		}
		System.out.println("storedObject value = " + this.storedObject);
	}
	
	public Node(Node parent, Comparable storedObject) {
		super();
		this.isNull = false;
		this.parent = parent;
		this.storedObject = storedObject;
		this.setRed(true);
		this.setLeft(new Node(true));
		this.setRight(new Node(true));
	}
	
	
	
	public Node(boolean isNull) {
		super();
		if (isNull == true) {
			this.setRed(false);			
		}
		this.isNull = true;		
	}

	public Node getGrandParent() {
		return this.getParent().getParent();
	}
	
	public Node getUncle() {
		if (this.getGrandParent() == null) {
			return null;	//если нет деда, то нет и дяди
		}
		if (this.getParent() == this.getGrandParent().getLeft()) {
			return this.getGrandParent().getRight();
		}
		else {
			return this.getGrandParent().getLeft();
		}
	}
	
	public Node getOnlyLeaf() {
		if (this.getLeft().isNull == false && this.getRight().isNull == true) {
			return this.getLeft();
		}
		else if (this.getLeft().isNull == true && this.getRight().isNull == false) {
			return this.getRight();
		}
		return null;
	}
	
	public void replaceWith(Node newNode) {
		//сначала проверяем, не является ли заменяемый узел корнем дерева
		if (this.getParent() != null) {
			if (this == this.getParent().getLeft()) {
				this.getParent().setLeft(newNode);
			}
			else {
				this.getParent().setRight(newNode);
			}
			newNode.setParent(this.getParent());
		}
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	public Comparable getStoredObject() {
		return storedObject;
	}

	public void setStoredObject(Comparable storedObject) {
		this.storedObject = storedObject;
	}
	
}
