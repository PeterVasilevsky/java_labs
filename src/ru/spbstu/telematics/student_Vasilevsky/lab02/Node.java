package ru.spbstu.telematics.student_Vasilevsky.lab02;

public class Node <T extends Comparable<T>> {
	private Node<T> parent;
	private Node<T> left;
	private Node<T> right;
	private boolean isRed;
	private T storedObject;
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
	
	public Node(Node<T> parent, T storedObject) {
		super();
		this.isNull = false;
		this.parent = parent;
		this.storedObject = storedObject;
		this.setRed(true);
		this.setLeft(new Node<T>(true));
		this.setRight(new Node<T>(true));
	}
	
	
	
	public Node(boolean isNull) {
		super();
		if (isNull == true) {
			this.setRed(false);			
		}
		this.isNull = true;		
	}

	public Node<T> getGrandParent() {
		return this.getParent().getParent();
	}
	
	public Node<T> getUncle() {
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
	
	public Node<T> getOnlyLeaf() {
		if (this.getLeft().isNull == false && this.getRight().isNull == true) {
			return this.getLeft();
		}
		else if (this.getLeft().isNull == true && this.getRight().isNull == false) {
			return this.getRight();
		}
		return null;
	}
	
	public void replaceWith(Node<T> newNode) {
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

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	public T getStoredObject() {
		return storedObject;
	}

	public void setStoredObject(T storedObject) {
		this.storedObject = storedObject;
	}
	
}
