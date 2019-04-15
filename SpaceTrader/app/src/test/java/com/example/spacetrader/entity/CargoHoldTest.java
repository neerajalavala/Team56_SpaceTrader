package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PurchaseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CargoHoldTest {
    CargoHold cargoHold;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        cargoHold = new CargoHold(15, 0);
    }

    @After
    public void tearDown() {
        cargoHold = null;
    }

    @Test()
    public void addGoodsInvalidName() throws PurchaseException {
        exceptionRule.expect(PurchaseException.class);
        exceptionRule.expectMessage("Illegal good name: [NAN]!");
        cargoHold.addGoods("[NAN]", 5);
    }

    @Test()
    public void addGoodsNoSpace() throws PurchaseException {
        exceptionRule.expect(PurchaseException.class);
        exceptionRule.expectMessage("No room in hold!");
        cargoHold.addGoods("Water", 55);
    }

    @Test()
    public void addGoodsValid() throws PurchaseException {
        cargoHold.addGoods("Furs", 5);
        assertEquals(cargoHold.getHold().get("Furs").getQuantity(), 5);
        assertEquals((int) cargoHold.getCount(), 5);
    }
}