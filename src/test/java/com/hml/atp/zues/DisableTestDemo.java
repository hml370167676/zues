package com.hml.atp.zues;

import org.testng.annotations.Test;

public class DisableTestDemo extends CaseBase {

    @Test(enabled = true)
    public void testMethodOne() {
        System.out.println("Test method one.");
    }

    @Test(enabled = false)
    public void testMethodTwo() {
        System.out.println("Test method two.");
    }

    @Test
    public void testMethodThree() {
        System.out.println("Test method three.");
    }
}
