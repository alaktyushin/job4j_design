package ru.job4j.design.lsp;

import org.junit.Test;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {

    @Test
    public void whenFoodGoesWrightAndCalendarIsLenient() {
        Calendar.Builder cal = new Calendar.Builder();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        Food milk1 = new Milk(
                "milk1",
                cal.setDate(2022, 5, 1).build(),
                cal.setDate(2022, 5, 1).build(),
                100);
        Food milk2 = new Milk(
                "milk2",
                cal.setDate(2022, 5, 1).build(),
                cal.setDate(2032, 5, 1).build(),
                220);
        Food milk3 = new Milk(
                "milk3",
                cal.setDate(2021, 5, 1).build(),
                cal.setDate(2023, 10, 1).build(),
                320);
        Food juice1 = new Juice(
                "juice1",
                cal.setDate(2022, 5, 1).build(),
                cal.setDate(2022, 5, 17).build(),
                120);
        Food juice2 = new Juice(
                "juice2",
                cal.setDate(2022, 5, 1).build(),
                cal.setDate(2022, 5, 32).build(),
                110);
        cq.distribute(milk1);
        cq.distribute(milk2);
        cq.distribute(milk3);
        cq.distribute(juice1);
        cq.distribute(juice2);
        List<String> expectedWarehouse = List.of(
                "Food{name='milk2', createDate=2022-06-01, expiryDate=2032-06-01, price=220.0, discount=0.0}");
        List<String> expectedShop = List.of(
                "Food{name='milk3', createDate=2021-06-01, expiryDate=2023-11-01, price=320.0, discount=0.0}," +
                        " Food{name='juice1', createDate=2022-06-01, expiryDate=2022-06-17, price=120.0, discount=0.5}," +
                        " Food{name='juice2', createDate=2022-06-01, expiryDate=2022-07-02, price=110.0, discount=0.0}");
        List<String> expectedTrash = List.of(
                "Food{name='milk1', createDate=2022-06-01, expiryDate=2022-06-01, price=100.0, discount=0.0}");
        assertThat(warehouse.getFoodList().toString(), is(expectedWarehouse.toString()));
        assertThat(shop.getFoodList().toString(), is(expectedShop.toString()));
        assertThat(trash.getFoodList().toString(), is(expectedTrash.toString()));
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void whenFoodGoesWrightAndCalendarIsNonLenient() {
        Calendar.Builder cal = new Calendar.Builder();
        cal.setLenient(false);
        Food milk1 = new Milk(
                "milk1",
                cal.setDate(2022, 5, 1).build(),
                cal.setDate(2022, 5, 31).build(),
                100);
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void whenFoodGoesWrightAndCreateDateBiggerThenExpireDate() {
        Calendar.Builder cal = new Calendar.Builder();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        Food milk1 = new Milk(
                "milk1",
                cal.setDate(2022, 5, 2).build(),
                cal.setDate(2022, 5, 1).build(),
                100);
        cq.distribute(milk1);
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void whenFoodGoesWrightAndCreateDateBiggerThenNow() {
        Calendar.Builder cal = new Calendar.Builder();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        Food milk1 = new Milk(
                "milk1",
                cal.setDate(2022, 5, 1).build(),
                cal.setDate(2022, 5, 1).build(),
                100);
        Food milk2 = new Milk(
                "milk2",
                cal.setDate(2022, 5, 17).build(),
                cal.setDate(2032, 5, 1).build(),
                220);
        cq.distribute(milk1);
        cq.distribute(milk2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenEmptyFood() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        Food milk = null;
        cq.distribute(milk);
    }
}