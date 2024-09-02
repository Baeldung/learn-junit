package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Campaign;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class CampaignServiceAssertionsTest {

    @Test
    void testAssertTrue() {
        Campaign testCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        assertTrue(testCampaign.getCode().equals("01"));
    }

    @Test
    void testAssertFalse() {
        Campaign testCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        assertFalse(testCampaign.getCode().equals("02"));
    }

    @Test
    void testAssertNull() {
        Campaign testCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");

        // Set campaign description to null
        testCampaign.setDescription(null);

        assertNull(testCampaign.getDescription());
    }

    @Test
    void testAssertNotNull() {
        Campaign testCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        assertNotNull(testCampaign.getDescription());
    }

    @Test
    void testAssertOptional() {
        Campaign testCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        Optional <Campaign> optionalCampaign = Optional.of(testCampaign);

        assertTrue(optionalCampaign.isPresent());
        assertFalse(optionalCampaign.isEmpty());
    }

    @Test
    void testAssertEquals() {
        Campaign firstCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        Campaign secondCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");

        assertEquals(firstCampaign, secondCampaign);
    }

    @Test
    void testAssertNotEquals() {
        Campaign firstCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        Campaign secondCampaign = new Campaign("02", "Campaign-02", "Test campaign description-02");

        // Compare names of campaign
        assertNotEquals(firstCampaign.getName(), secondCampaign.getName());
    }

    @Test
    void testAssertSame() {
        Campaign firstCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");

        // Both objects point to the same memory locations
        Campaign secondCampaign = firstCampaign;

        assertSame(firstCampaign, secondCampaign);
    }

    @Test
    void testAssertNotSame() {
        Campaign firstCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");
        Campaign secondCampaign = new Campaign("01", "Campaign-01", "Test campaign description-01");

        assertNotSame(firstCampaign, secondCampaign);
    }

    @Test
    void testAssertArrayEquals() {
        // Create first array of campaigns
        Campaign [] firstCampaign = new Campaign[2];
        firstCampaign[0] = new Campaign("01", "Campaign-01", "Test campaign description-01");
        firstCampaign[1] = new Campaign("02", "Campaign-02", "Test campaign description-02");

        // Create second array of campaigns
        Campaign [] secondCampaign = new Campaign[2];
        secondCampaign[0] = new Campaign("01", "Campaign-01", "Test campaign description-01");
        secondCampaign[1] = new Campaign("02", "Campaign-02", "Test campaign description-02");

        assertArrayEquals(firstCampaign, secondCampaign);
    }

    @Test
    void testAssertArrayEqualsNull() {
        Campaign [] firstCampaign = null;
        Campaign [] secondCampaign = null;

        assertArrayEquals(firstCampaign, secondCampaign);
    }

    @Test
    void testAssertIterableEquals() {
        // Create iterable using the ArrayList backing data structure
        Iterable<Campaign> ArrayListIterable = new ArrayList<>(asList(
                new Campaign("01", "Campaign-01", "Test campaign description-01"),
                new Campaign("02", "Campaign-02", "Test campaign description-02")
        ));

        // Create iterable using the LinkedList backing data structure
        Iterable<Campaign> LinkedListIterable = new LinkedList<>(asList(
                new Campaign("01", "Campaign-01", "Test campaign description-01"),
                new Campaign("02", "Campaign-02", "Test campaign description-02")
        ));

        assertIterableEquals(ArrayListIterable, LinkedListIterable);
    }

    @Test
    void testAssertIterableEqualsNull() {
        Iterable<Campaign> ArrayListIterable = null;
        Iterable<Campaign> LinkedListIterable = null;

        assertIterableEquals(ArrayListIterable, LinkedListIterable);
    }
}
