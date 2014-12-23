package com.tejava.practical;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SeveralCalendarEventListTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testInsertSingleEvent() throws Exception {
EventList EventList1 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup;
		testid = 0;
	
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<3;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 12, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					testid++;
				
				
				}	
			}			
		}
		
		assertEquals(1200, testid);
		assertEquals(testid, EventList1.GetSize());
	}

	@Test
	public void testInsertIntIntIntIntIntIntIntIntStringString()
			throws Exception {
EventList EventList1 = new EventList(null);
		
		EventList EventList2 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup;
		testid = 0;
	
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<3;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 12, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					EventList2.Insert(14, 12, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					testid++;
				
				
				}	
			}			
		}
		
		assertEquals(1200, testid);
		assertEquals(EventList1.GetSize(), EventList2.GetSize());
	}

	@Test
	public void testDeleteInt() throws Exception {
EventList EventList1 = new EventList(null);
		
		int tid, testid, testday, testHour, testEventGroup;
		testid = 0;
	
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<3;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 12, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					testid++;
				
				
				}	
			}			
		}
		for (tid=1;tid<601;tid++){
			EventList1.Delete(tid);
		}
		
		assertEquals(1200, testid);
		assertEquals(600, EventList1.GetSize());
	}

	@Test
	public void testDeleteSingleEvent() throws Exception {
EventList EventList1 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup;
		testid = 0;
	
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<3;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 12, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					EventList1.Delete(SingleEvent1);
					testid++;
				
				
				}	
			}			
		}
		
		assertEquals(1200, testid);
		assertEquals(0, EventList1.GetSize());
	}

	@Test
	public void testGetSize() throws Exception {
EventList EventList1 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup;
		testid = 0;
	
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<6;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 12, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					testid++;
				
				
				}	
			}			
		}

		
		assertEquals(3000, testid);
		assertEquals(3000, EventList1.GetSize());
	}

	@Test
	public void testSearchIntIntInt() throws Exception {
EventList EventList1 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup;
		testid = 0;
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<2;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 11, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					
					testid++;
				
				
				}	
			}			
		}	
		for(testday=1;testday<31;testday++){
			ArrayList<SingleEvent> newEventList1 = EventList1.Search(14,11,testday);
			assertEquals(20,newEventList1.size());


		}
		
		assertEquals(600, testid);
		assertEquals(600, EventList1.GetSize());
	}

	@Test
	public void testSearchIntIntIntIntIntInt() throws Exception {
EventList EventList1 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup;
		testid = 0;
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<2;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 11, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					
					testid++;
				
				
				}	
			}			
		}	
		for(testday=1;testday<30;testday++){
			ArrayList<SingleEvent> newEventList1 = EventList1.Search(14,11,testday,14,11,testday+1);
			assertEquals(40,newEventList1.size());


		}
		
		assertEquals(600, testid);
		assertEquals(600, EventList1.GetSize());

	}

	@Test
	public void testSearchIntIntIntInt() throws Exception {
EventList EventList1 = new EventList(null);
		
		int testid, testday, testHour, testEventGroup, testsize;
		testid = 0;
		for(testday=1;testday<31;testday++){
			for(testHour=1;testHour<21;testHour++){
				for(testEventGroup=1;testEventGroup<2;testEventGroup++){
					SingleEvent SingleEvent1 = new SingleEvent(0,14, 11, testday, testHour, testHour, 10, 30,"test"+testid , "Description", testEventGroup, 1, 1, "test");
					EventList1.Insert(SingleEvent1);
					
					testid++;
				
				
				}	
			}			
		}	
		for(testday=1;testday<30;testday++){
			for(testsize=1;testsize<21;testsize++){
				ArrayList<SingleEvent> newEventList1 = EventList1.Search(14,11,testday,testsize);
				assertEquals(testsize,newEventList1.size());
			}
			
		}
		
		assertEquals(600, testid);
		assertEquals(600, EventList1.GetSize());
	}

}
