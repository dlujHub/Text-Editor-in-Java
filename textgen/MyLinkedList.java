package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("I can't add null element, moron!");
		}
		tail.data = element;
		LLNode<E> newtail = new LLNode<>(null);
		tail.next = newtail;
		newtail.prev = tail;
		tail = newtail;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		return getCurrent(index).data;
	}
	public LLNode<E> getCurrent(int index) {
		LLNode<E> curr;
		if (size !=0 && index<size && index >=0) {

			if (index < size/2) {
				curr = head;
				for (int i=0; i<=index; i++) {
					curr = curr.next;
				}
			} else {
				curr = tail;
				for (int i=size; i>index; i--) {
					curr = curr.prev;
				}
			}
		} else {
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		return curr;
	}
	/**
	 * Add an element to the list at the specified index
	 * @param index The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (size == 0) {
			add(element);
		}
		if (index >= 0 && index < size) {
			LLNode<E> curr = new LLNode<E>(element);
			LLNode<E> next = getCurrent(index);
			curr.prev = next.prev;
			curr.next = next;
			next.prev = curr;
			LLNode<E> prev = curr.prev;
			prev.next  = curr;
			size++;
		} else {
			throw new IndexOutOfBoundsException("Invalid index!");
		}

	}


	/** Return the size of the list */
	public int size() 
	{

		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{

		if (index >= 0 && index < size) {
			LLNode<E> curr = getCurrent(index);
			E currElement = curr.data;
			LLNode<E> prev = curr.prev;
			LLNode<E> next = curr.next;
			prev.next = next;
			next.prev = prev;
			size--;
			return currElement;
		} else {
			throw new IndexOutOfBoundsException("Invalid index!");
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (size == 0) {
			add(element);
			return null;
		}
		if (index >= 0 && index < size) {
			LLNode<E> curr = getCurrent(index);
			E currElement = curr.data;
			curr.data = element;
			return currElement;
		} else {
			throw new IndexOutOfBoundsException("Invalid index!");
		}
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
