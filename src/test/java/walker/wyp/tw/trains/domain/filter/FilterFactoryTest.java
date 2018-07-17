package walker.wyp.tw.trains.domain.filter;

import static org.junit.Assert.*;

import org.junit.Test;

import walker.wyp.tw.trains.domain.filter.FilterCollection;
import walker.wyp.tw.trains.domain.filter.FilterFactory;

public class FilterFactoryTest {	
	@Test
	public void testDisplay() {
		assertNotNull(FilterFactory.createDisplay(FilterCollection.DISTANCE_FILTER));
		assertNotNull(FilterFactory.createDisplay(FilterCollection.PATH_NUM_FILTER));		
	}
}
