package ch.bfh.ti.sed.patmon1.server.model.sbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

public class DoctorSBOTest {
	
	@Test
	public void testInitUsername() {
		String username = "Christoph";
		String password = "hpotsirhc";
		DoctorSBO doctor = new DoctorSBO(username,password);
		assertEquals(username, doctor.getUsername());
	}

	@Test
	public void testComparePassword(){
		String username = "Christoph";
		String password = "hpotsirhc";
		DoctorSBO doctor = new DoctorSBO(username,password);
		assertTrue("comparepassword not implemented correctly",doctor.comparePassword(password));
		
		DoctorSBO doctorPasswordNull = new DoctorSBO(username,null);
		assertFalse("comparepassword should not return true if stored password is empty",doctorPasswordNull.comparePassword(null));
	}
	
	
	
}
