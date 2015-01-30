/*

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinaryNodeOld<T extends Comparable<T>> {
	
	public static BinaryNodeOld NULL_NODE= new BinaryNodeOld<Integer>(1);
	
	private T element;
	private BinaryNodeOld<T> leftChild;
	private BinaryNodeOld<T> rightChild;
	
	public BinaryNodeOld(T element){
		this.element = element;
		this.leftChild = NULL_NODE;
		this.rightChild = NULL_NODE;		
	}
	
	public void setLeftChild(BinaryNodeOld<T> leftChild){
		this.leftChild = leftChild;
	}
	
	public void setRightChild(BinaryNodeOld<T> rightChild){
		this.rightChild = rightChild;
	}


	public int height(){
		if(this == NULL_NODE)
			return -1;
		return 1 + Math.max(this.leftChild.height(),this.rightChild.height());
		
	}

	public int size() {
		if(this == NULL_NODE) 
			return 0;
		return 1 + this.leftChild.size() + this.rightChild.size();
	}

	public void toArrayList(ArrayList<T> toReturn) {
		if(this == NULL_NODE)
			return;
		
		this.leftChild.toArrayList(toReturn);
		toReturn.add(element);
		this.rightChild.toArrayList(toReturn);
	}
	
	public boolean contains(T o){
		if(this == NULL_NODE)
			return false;
		return this.element.equals(o) || this.leftChild.contains(o) || this.rightChild.contains(o); 
	}

	
	@Override
	public String toString() {
		return this.element.toString();
	}
	
	public BinaryNodeOld<T> getLeftChild(){
		return this.leftChild;
	}

	public BinaryNodeOld<T> getRightChild() {
		return this.rightChild;
	}

	public BinaryNodeOld<T> insert(T o) throws Duplicate {
		if(this == NULL_NODE){
			return new BinaryNodeOld<T>(o);
		}
		
		int value = this.element.compareTo(o);
		if(value > 0){
			this.leftChild = this.leftChild.insert(o);
		}else if(value < 0){
			this.rightChild = this.rightChild.insert(o);
		}else{
			throw new Duplicate();
		}
		return this;
	}
	
	public T getElement(){
		return element;
	}
	
	public BinaryNodeOld<T> remove(T o){
		if(this == NULL_NODE){
			throw new NoSuchElementException();
		}
		
		int value = this.element.compareTo(o);
		if(value > 0){
			this.leftChild = this.leftChild.remove(o);
		}else if(value < 0){
			this.rightChild = this.rightChild.remove(o);
		}else{
			if(leftChild == NULL_NODE){
				return rightChild;
			}else if (rightChild == NULL_NODE){
				return leftChild;
			}else{
				T temp = leftChild.rightMost();
				remove(temp);
				this.element = temp;
			}
		}
		return this;
	}
	
	public T rightMost(){
		if(rightChild == NULL_NODE)
			return this.element;
		return this.rightChild.rightMost();
	}
	
}

*/