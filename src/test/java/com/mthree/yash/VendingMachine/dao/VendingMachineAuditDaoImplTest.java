package com.mthree.yash.VendingMachine.dao;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineAuditDaoImplTest {

    String testAuditFile = "testAuditFile.txt";
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoImpl(testAuditFile);


    public VendingMachineAuditDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testWriteAuditEntry() throws VendingMachinePersistenceException {
        //ARRANGE
        String entry = "One Snickers removed.";

        //ACT
        testAuditDao.writeAuditEntry(entry);

    }

}