/**
 * 
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;


	@Before
	public void setUp() {
	    shortList = new MyLinkedList<>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<>();
		longerList = new MyLinkedList<>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}
	
	/** Test if the get method is working correctly.
	 */

	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}



		//test longer list contents
	for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
	}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * */
	@Test
	public void testRemove()
	{

			System.out.println(list1);

		int a = list1.remove(0);
		System.out.println(list1);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		list1.remove(1);
		System.out.println(list1);
		list1.remove(0);
		System.out.println(list1);


		System.out.println(longerList);;

		for (int i = 2; i < 4; i++)
		{
			longerList.remove(i);
		}
		System.out.println(longerList);

	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		longerList = new MyLinkedList<Integer>();
		for (int i=0; i<500; i++) {
			longerList.add(i);
		}
		for (int i=0; i<500; i++) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
			System.out.println(longerList.get(i));
		}

		try {
			longerList.get(-14);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(9546);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}


		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		System.out.println(shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{

		longerList = new MyLinkedList<Integer>();

		for (int i=0; i<100; i++) {
			longerList.add(i, i);
		}
		for (int i=0; i<100; i++) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
			System.out.println(longerList.get(i));
		}


		try {
			longerList.add(102, 49);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			longerList.add(-1, 49);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	   shortList.set(1, "P");
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			System.out.print(longerList.get(i) + " ");
		}

		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.set(i, i+1);
		}
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			System.out.print(longerList.get(i) + " ");
		}

		try {
			longerList.set(-1,95);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.set(400,95);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		emptyList.set(0, 4);

		System.out.print("\n" + emptyList.get(0) );
	}

	
}
