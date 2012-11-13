package ru.spbstu.telematics.student_Vasilevsky.lab02;

public class RedBlackTree<T extends Comparable<T>> implements IRedBlackTree<T> {
	private Node<T> root;
	
	
	public Node<T> getRoot() {
		return root;
	}
	
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	@Override
	public void add(T e) {
		this.insert(e, this.getRoot());
	}
	
	@Override
	public boolean remove(T o) {
		boolean returnValue = false;
		Node<T> nodeToRemove = this.find(o, this.getRoot());
		while (nodeToRemove != null) {
			System.out.println("in loop");
			this.deleteNode(nodeToRemove);
			returnValue = true;
			nodeToRemove = this.find(o, this.getRoot());
		}
		
		return returnValue;
	}

	@Override
	public boolean contains(T o) {
		if (this.find(o, this.getRoot()) != null)
			return true;
		return false;
	}

	private void insert(T e, Node<T> parent) {
		if (parent == null && this.getRoot() == null) {
			parent = new Node<T>(null, e);
			this.setRoot(parent);
			parent.setRed(false);
			System.out.println("Root node added");
		}
		//если e больше, чем предок
		else if (e.compareTo(parent.getStoredObject()) >= 0) {
			if (parent.getRight().isNull() == true) {
				parent.setRight(new Node<T>(parent, e));
				System.out.println("right");
				//проверка баланса
				this.checkBalance1(parent.getRight());
			}
			else {
				//рекурсия блджад!!!11
				this.insert(e, parent.getRight());
			}
		}
		//если е меньше чем предок
		else {
			if (parent.getLeft().isNull() == true) {
				System.out.println("left");
				parent.setLeft(new Node<T>(parent, e));
				//проверка баланса
				this.checkBalance1(parent.getLeft());
			}
			else {
				//рекурсия блджад!!!11
				this.insert(e, parent.getLeft());
			}
		}
	}

	private void checkBalance0(Node<T> aNode) {
		if (aNode.getParent() == null) {
			aNode.setRed(false);
		}
		else {
			this.checkBalance1(aNode);
		}
		
	}
	
	private void checkBalance1(Node<T> aNode) {	
		if (aNode.getParent().isRed() == false) {
			return;
		}
		else {
			this.checkBalance2(aNode);
		}
	}

	private void checkBalance2(Node<T> aNode) {
		if ((aNode.getUncle().isNull() == false) && (aNode.getUncle().isRed() == true) && (aNode.getParent().isRed() == true)) {
			aNode.getParent().setRed(false);
			aNode.getUncle().setRed(false);
			aNode.getGrandParent().setRed(true);
			checkBalance0(aNode.getGrandParent());
		}
		else {
			checkBalance3(aNode);
		}
	}

	private void checkBalance3(Node<T> aNode) {
		if ((aNode == aNode.getParent().getRight()) && (aNode.getParent() == aNode.getGrandParent().getLeft())) {
			this.rotateLeft(aNode.getParent());
			aNode = aNode.getLeft();
		}
		else if ((aNode == aNode.getParent().getLeft()) && (aNode.getParent() == aNode.getGrandParent().getRight())) {
			this.rotateRight(aNode.getParent());
			aNode = aNode.getRight();
		}
		this.checkBalance4(aNode);
	}

	private void checkBalance4(Node<T> aNode) {
		aNode.getParent().setRed(false);
		aNode.getGrandParent().setRed(true);
		if ((aNode == aNode.getParent().getLeft()) && (aNode.getParent() == aNode.getGrandParent().getLeft())) {
			this.rotateRight(aNode.getGrandParent());
		}
		else {
			this.rotateLeft(aNode.getGrandParent());
		}
		
	}
	
	private void rotateLeft(Node<T> aNode) {	
		Node<T> y = aNode.getRight();
		aNode.setRight(y.getLeft());
		
		if (y.getLeft().isNull() == false) {
			y.getLeft().setParent(aNode);
		}
		y.setParent(aNode.getParent());
		
		if (aNode.getParent() == null) {
			this.setRoot(y);
		} else {
			if (aNode == aNode.getParent().getLeft()) {
				aNode.getParent().setLeft(y);
			} else {
				aNode.getParent().setRight(y);
			}
		}
		y.setLeft(aNode);
		aNode.setParent(y);
	}
	
	private void rotateRight(Node<T> aNode) {	
		Node<T> y = aNode.getLeft();
		aNode.setLeft(y.getRight());
		
		if (y.getRight().isNull() == false) {
			y.getRight().setParent(aNode);
		}
		y.setParent(aNode.getParent());
		
		if (aNode.getParent() == null) {
			this.setRoot(y);
		} else {
			if (aNode == aNode.getParent().getRight()) {
				aNode.getParent().setRight(y);
			} else {
				aNode.getParent().setLeft(y);
			}
		}
		y.setRight(aNode);
		aNode.setParent(y);
	}
	



	private Node<T> find(T desiredObject, Node<T> parent) {
		if (this.getRoot() == null) {
			System.out.println("Tree is empty");
			return null;
		}
		
		if (parent.isNull() == true) {
			System.out.println("No such object");
			return null;
		}
		if (desiredObject.compareTo(parent.getStoredObject()) == 0) {
			System.out.println("Node has been found");
			return parent;
		}
		else if (desiredObject.compareTo(parent.getStoredObject()) > 0) {
			parent = parent.getRight();
			return this.find(desiredObject, parent);
		}
		else if (desiredObject.compareTo(parent.getStoredObject()) < 0) {
			parent = parent.getLeft();
			return this.find(desiredObject, parent);
		}
		return null;
		
	}
	
	private Node<T> sibling(Node<T> aNode) {
//		struct node *
//		sibling(struct node *n)
//		{
//		        if (n == n->parent->left)
//		                return n->parent->right;
//		        else
//		                return n->parent->left;
//		}
		if (aNode == aNode.getParent().getLeft()) {
			return aNode.getParent().getRight();
		}
		else {
			return aNode.getParent().getLeft();
		}
	}
	
	public void deleteNode(Node<T> aNode) {
		if (aNode.getLeft().isNull() == false && aNode.getRight().isNull() == false) {		//если у удаляемого узла два потомка
			this.deleteTwoChilds(aNode);
		} else { 								//если у удаляемого узла не более одного потомка
			this.deleteOneChild(aNode);
		}
	}
	
	private void deleteTwoChilds (Node<T> aNode) {
		Node<T> successor = this.successor(aNode);
		aNode.setStoredObject(successor.getStoredObject());
		this.deleteNode(successor);		
	}

	private Node<T> successor(Node<T> aNode) {
		if (aNode.getLeft().isNull() == false) {
			aNode = aNode.getLeft();
			while (aNode.getRight().isNull() == false) {
				aNode = aNode.getRight();
			}
			return aNode;
		}
		return null;
	}
	
	private void deleteOneChild (Node<T> aNode) {
//		void
//		delete_one_child(struct node *n)
//		{
//		        /*
//		         * Условие: n имеет не более одного ненулевого потомка.
//		         */
//		        struct node *child = is_leaf(n->right) ? n->left : n->right;
//		 
//		        replace_node(n, child);
//		        if (n->color == BLACK) {
//		                if (child->color == RED)
//		                        child->color = BLACK;
//		                else
//		                        delete_case1(child);
//		        }
//		        free(n);
//		}
		
//		Node child = aNode.getOnlyLeaf();
		Node<T> child;
		if (aNode.getRight().isNull())
			child = aNode.getLeft();
		else
			child = aNode.getRight();
		
//		aNode.replaceWith(child);
		this.replaceNode(aNode, child);
		if (aNode.isRed() == false) {
			if (child.isRed() == true) {
				child.setRed(false);
			}
			else {
				this.deleteCase1(child);
			}
		}
	}

	private void replaceNode(Node<T> aNode, Node<T> child) {
		if (aNode.getParent() != null) {
			if (aNode == aNode.getParent().getLeft())
				aNode.getParent().setLeft(child);
			else
				aNode.getParent().setRight(child);
			child.setParent(aNode.getParent());
		}
		else
			this.setRoot(child);	
	}

	private void deleteCase1(Node<T> aNode) {
//		void
//		delete_case1(struct node *n)
//		{
//		        if (n->parent != NULL)
//		                delete_case2(n);
//		}
		
		if (aNode.getParent().isNull() == false) {
			deleteCase2(aNode);
		}
		
	}

	private void deleteCase2(Node<T> aNode) {
//		void delete_case2(struct node *n)
//		{
//		        struct node *s = sibling(n);
//		 
//		        if (s->color == RED) {
//		                n->parent->color = RED;
//		                s->color = BLACK;
//		                if (n == n->parent->left)
//		                        rotate_left(n->parent);
//		                else
//		                        rotate_right(n->parent);
//		        } 
//		        delete_case3(n);
//		}
		
		Node<T> s = this.sibling(aNode);
		if (s.isRed() == true) {
			aNode.getParent().setRed(true);
			s.setRed(false);
			if (aNode == aNode.getParent().getLeft())
				this.rotateLeft(aNode.getParent());
			else
				this.rotateRight(aNode.getParent());
		}
		this.deleteCase3(aNode);
		
	}

	private void deleteCase3(Node<T> aNode) {
//		void delete_case4(struct node *n)
//		{
//		        struct node *s = sibling(n);
//		 
//		        if ((n->parent->color == RED) &&
//		            (s->color == BLACK) &&
//		            (s->left->color == BLACK) &&
//		            (s->right->color == BLACK)) {
//		                s->color = RED;
//		                n->parent->color = BLACK;
//		        } else
//		                delete_case5(n);
//		}
		
		Node<T> s = this.sibling(aNode);
		
		if ((aNode.getParent().isRed() == true) &&
			(s.isRed() == false) &&
			(s.getLeft().isRed() == false) &&
			(s.getRight().isRed() == false)) {
			s.setRed(true);
			aNode.getParent().setRed(false);
		}
		else
			deleteCase5(aNode);
		
	}

	private void deleteCase5(Node<T> aNode) {
//		void delete_case5(struct node *n)
//		{
//		        struct node *s = sibling(n);
//		 
//		        if  (s->color == BLACK) { /* this if statement is trivial, 
//		due to case 2 (even though case 2 changed the sibling to a sibling's child, 
//		the sibling's child can't be red, since no red parent can have a red child). */
//		/* the following statements just force the red to be on the left of the left of the parent, 
//		   or right of the right, so case six will rotate correctly. */
//		                if ((n == n->parent->left) &&
//		                    (s->right->color == BLACK) &&
//		                    (s->left->color == RED)) { /* this last test is trivial too due to cases 2-4. */
//		                        s->color = RED;
//		                        s->left->color = BLACK;
//		                        rotate_right(s);
//		                } else if ((n == n->parent->right) &&
//		                           (s->left->color == BLACK) &&
//		                           (s->right->color == RED)) {/* this last test is trivial too due to cases 2-4. */
//		                        s->color = RED;
//		                        s->right->color = BLACK;
//		                        rotate_left(s);
//		                }
//		        }
//		        delete_case6(n);
//		}
		
		Node<T> s = this.sibling(aNode);
		
		if (s.isRed() == false) {
			if (aNode == aNode.getParent().getLeft() &&
				s.getRight().isRed() == false &&
				s.getLeft().isRed() == true) {
				s.setRed(true);
				s.getLeft().setRed(false);
				this.rotateRight(s);
			}
			else if ((aNode == aNode.getParent().getRight()) &&
					(s.getLeft().isRed() == false) &&
					(s.getRight().isRed() == true)) {
				s.setRed(true);
				s.getRight().setRed(false);
				this.rotateLeft(s);
			}
		}
		this.deleteCase6(aNode);
		
	}

	private void deleteCase6(Node<T> aNode) {
//		void delete_case6(struct node *n)
//		{
//		        struct node *s = sibling(n);
//		 
//		        s->color = n->parent->color;
//		        n->parent->color = BLACK;
//		 
//		        if (n == n->parent->left) {
//		                s->right->color = BLACK;
//		                rotate_left(n->parent);
//		        } else {
//		                s->left->color = BLACK;
//		                rotate_right(n->parent);
//		        }
//		}
		
		Node<T> s = this.sibling(aNode);
		
		s.setRed(aNode.getParent().isRed());
		aNode.getParent().setRed(false);
		
		if (aNode == aNode.getParent().getLeft()) {
			s.getRight().setRed(false);
			this.rotateLeft(aNode.getParent());
		}
		else {
			s.getLeft().setRed(false);
			this.rotateRight(aNode.getParent());
		}
	}
}