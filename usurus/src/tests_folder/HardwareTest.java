package tests_folder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.HardwareDao;
import domain.Hardware;
import junit.framework.Assert;

class HardwareTest {

	@Test
	void testRegistrarHardware() {
		HardwareDao x = new HardwareDao();
		Hardware h = new Hardware("jsd","jsdfdnfs","jofdjf","jdsfdw","jsdfnsd","fjdsf");
		boolean esperado = true;
		boolean obtenido = x.registrarHardware(h); 
		
		Assert.assertEquals(esperado, obtenido);
	}

}
