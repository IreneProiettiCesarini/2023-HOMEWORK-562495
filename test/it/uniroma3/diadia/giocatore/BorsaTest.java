package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	public final static Attrezzo ATTREZZO = new Attrezzo("abc", 1);

	private Borsa borsa;
	private Borsa borsaHW3;

	@Before
	public void setUp() {
		this.borsa = new Borsa(DEFAULT_PESO_MAX_BORSA);
		this.borsa.addAttrezzo(ATTREZZO);

		this.borsaHW3 = new Borsa(DEFAULT_PESO_MAX_BORSA);
		Attrezzo piombo = new Attrezzo("piombo", 5);
		borsaHW3.addAttrezzo(piombo);
		Attrezzo ps = new Attrezzo("ps", 2);
		borsaHW3.addAttrezzo(ps);
		Attrezzo piuma = new Attrezzo("piuma", 1);
		borsaHW3.addAttrezzo(piuma);
		Attrezzo libro = new Attrezzo("libro", 2);
		borsaHW3.addAttrezzo(libro);
	}

	@Test
	public void testAddAttrezzoNullo() {
		assertFalse(this.borsa.addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo("nuovo", 1);
		assertTrue(this.borsa.addAttrezzo(attrezzo));
		assertSame(attrezzo, this.borsa.getAttrezzo("nuovo"));
	}

	@Test
	public void testAddAttrezzoOmonimo() {
		assertTrue(this.borsa.getAttrezzi().containsValue(ATTREZZO));
		Attrezzo same = new Attrezzo("abc", 1);
		assertTrue(this.borsa.addAttrezzo(same));
		assertSame(same, this.borsa.getAttrezzi().get(ATTREZZO.getNome()));
	}

	@Test
	public void testAddAttrezzoOltrePesoMassimo() {
		Attrezzo attrezzo = new Attrezzo("pesante", 10);
		assertFalse(this.borsa.getAttrezzi().containsKey(attrezzo.getNome()));
		assertFalse(this.borsa.addAttrezzo(attrezzo));
		assertFalse(this.borsa.getAttrezzi().containsKey(attrezzo.getNome()));
	}

	@Test
	public void testGetAttrezzoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.isEmpty());
		assertNull(borsaVuota.getAttrezzo("abc"));
	}

	@Test
	public void testGetAttrezzoContenuto() {
		assertSame(ATTREZZO, this.borsa.getAttrezzo("abc"));
	}

	@Test
	public void testGetAttrezzoNonContenuto() {
		assertEquals(1, this.borsa.getAttrezzi().size());
		Attrezzo attrezzo = new Attrezzo("nonContenuto", 1);
		assertNull(this.borsa.getAttrezzo(attrezzo.getNome()));
		assertEquals(1, this.borsa.getAttrezzi().size());
	}

	@Test
	public void testGetPesoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertEquals(0, borsaVuota.getPeso());
	}

	@Test
	public void testGetPesoBorsaPiena() {
		assertTrue(this.borsa.addAttrezzo(new Attrezzo("perRiempire", 9)));
		assertEquals(DEFAULT_PESO_MAX_BORSA, this.borsa.getPeso());
	}

	@Test
	public void testHasAttrezzoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertFalse(borsaVuota.hasAttrezzo(ATTREZZO.getNome()));
	}

	@Test
	public void testHasAttrezzoContenuto() {
		assertTrue(this.borsa.hasAttrezzo(ATTREZZO.getNome()));
	}

	@Test
	public void testHasAttrezzoNonContenuto() {
		assertFalse(this.borsa.hasAttrezzo(new Attrezzo("nonContenuto", 1).getNome()));
	}

	@Test
	public void testIsEmptyBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.isEmpty());
	}

	@Test
	public void testIsEmptyBorsaNonVuota() {
		assertFalse(this.borsa.isEmpty());
	}

	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.isEmpty());
		assertNull(borsaVuota.removeAttrezzo(ATTREZZO.getNome()));
		assertTrue(borsaVuota.isEmpty());
	}

	@Test
	public void testRemoveAttrezzoContenuto() {
		assertFalse(this.borsa.isEmpty());
		assertSame(ATTREZZO, this.borsa.removeAttrezzo(ATTREZZO.getNome()));
		assertTrue(this.borsa.isEmpty());
	}

	@Test
	public void testRemoveAttrezzoNonContenuto() {
		assertFalse(this.borsa.isEmpty());
		assertNull(this.borsa.removeAttrezzo(new Attrezzo("nonContenuto", 1).getNome()));
	}

	@Test
	public void testToStringBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertEquals("Borsa vuota", borsaVuota.toString());
	}

	@Test
	public void testGetContenutoOrdinatoPerPesoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.getContenutoOrdinatoPerPeso().isEmpty());
		assertEquals(Collections.emptyList(), borsaVuota.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testGetContenutoOrdinatoPerPesoBorsaSingleton() {
		List<Attrezzo> listSingelton = Collections.singletonList(ATTREZZO);
		assertEquals(listSingelton, this.borsa.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoBorsaTripleton() {
		Attrezzo attrezzoDue = new Attrezzo("attDue", 3);
		Attrezzo attrezzoTre = new Attrezzo("attTre", 2);
		assertTrue(this.borsa.addAttrezzo(attrezzoDue));
		assertTrue(this.borsa.addAttrezzo(attrezzoTre));
		List<Attrezzo> listTripleton= new ArrayList<Attrezzo>();
		listTripleton.add(ATTREZZO);
		listTripleton.add(attrezzoTre);
		listTripleton.add(attrezzoDue);
		assertEquals(listTripleton, this.borsa.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testGetContenutoOrdinatoPerPesoBorsaDoubletonAttrezziEquipesanti() {
		Attrezzo attrezzoPrecc = new Attrezzo("aaz", 1);
		assertTrue(this.borsa.addAttrezzo(attrezzoPrecc));
		List<Attrezzo> listDoubleton= new ArrayList<Attrezzo>();
		listDoubleton.add(attrezzoPrecc);
		listDoubleton.add(ATTREZZO);
		assertEquals(listDoubleton, this.borsa.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testGetContenutoOrdinatoPerNomeBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.getContenutoOrdinatoPerNome().isEmpty());
		assertEquals(Collections.emptySortedSet(), borsaVuota.getContenutoOrdinatoPerNome());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNomeBorsaSingleton() {
		SortedSet<Attrezzo> setSingelton = new TreeSet<Attrezzo>();
		assertTrue(setSingelton.add(ATTREZZO));
		assertEquals(setSingelton, this.borsa.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testGetContenutoOrdinatoPerNomeBorsaDoubleton() {
		Attrezzo attrezzoPrec = new Attrezzo("aaz", 2);
		assertTrue(this.borsa.addAttrezzo(attrezzoPrec));
		SortedSet<Attrezzo> setDoubleton = new TreeSet<Attrezzo>();
		assertTrue(setDoubleton.add(ATTREZZO));
		assertTrue(setDoubleton.add(attrezzoPrec));
		assertEquals(setDoubleton, this.borsa.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testGetContenutoRaggiuppatoPerPesoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.getContenutoRaggruppatoPerPeso().isEmpty());
		assertEquals(Collections.emptyMap(), borsaVuota.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetContenutoRaggiuppatoPerPesoBorsaSingleton() {
		Map<Integer, Set<Attrezzo>> mappaSingleton = Collections.singletonMap(1, Collections.singleton(ATTREZZO));
		assertEquals(mappaSingleton, this.borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetContenutoRaggiuppatoPerPesoDoubleton() {
		Attrezzo attrezzoDue = new Attrezzo("attrezzoDue", 2);
		assertTrue(this.borsa.addAttrezzo(attrezzoDue));
		Map<Integer, Set<Attrezzo>> mappaDoubleton = new HashMap<Integer, Set<Attrezzo>>();
		mappaDoubleton.put(1, Collections.singleton(ATTREZZO));
		mappaDoubleton.put(2, Collections.singleton(attrezzoDue));
		assertEquals(mappaDoubleton, this.borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetContenutoRaggiuppatoPerPesoSingletonAttrezziEquipesanti() {
		Attrezzo attrezzoUno = new Attrezzo("attrezzoUno", 1);
		assertTrue(this.borsa.addAttrezzo(attrezzoUno));
		Set<Attrezzo> insiemeAttrezziEquipesanti = new HashSet<>(Arrays.asList(ATTREZZO, attrezzoUno));
		Map<Integer, Set<Attrezzo>> mappa = Collections.singletonMap(1, insiemeAttrezziEquipesanti);
		assertEquals(mappa, this.borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testCheckOrdinamenti() {
		System.out.println(this.borsaHW3.getContenutoOrdinatoPerPeso());
		System.out.println(this.borsaHW3.getContenutoOrdinatoPerNome());
		System.out.println(this.borsaHW3.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPesoBorsaVuota() {
		Borsa borsaVuota = new Borsa();
		assertTrue(borsaVuota.getContenutoRaggruppatoPerPeso().isEmpty());
		assertEquals(Collections.emptyMap(), borsaVuota.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPesoAttrezziEquipesanti() {
		Attrezzo pesanteUguale = new Attrezzo("bac", 2);
		assertTrue(this.borsa.addAttrezzo(pesanteUguale));
		SortedSet<Attrezzo> set = this.borsa.getSortedSetOrdinatoPerPeso();
		assertSame(ATTREZZO, set.first());
		assertSame(pesanteUguale, set.last());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		Attrezzo pesante = new Attrezzo("pesante", 5);
		assertTrue(this.borsa.addAttrezzo(pesante));
		SortedSet<Attrezzo> set = this.borsa.getSortedSetOrdinatoPerPeso();
		assertSame(ATTREZZO, set.first());
		assertSame(pesante, set.last());
	}

}
