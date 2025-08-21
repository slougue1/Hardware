# Milestone 1 - Traversity

## Table of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Video Walkthrough](#Video-Walkthrough)
4. [Wireframes](#Wireframes)

## Overview

### Description

App listing nearby attractions, food, hotels, and public tranportation based on a chosen city 

### App Evaluation


- **Category:**
    * Travel
- **Mobile:**
    * Use maps to pinpoint location
    * Real time data
    * Use camera to save photo with location taken
- **Story:**
    * Allows users to be able to easily identify and find places they can go that are nearby
    * Based on user's liking, suggest places to go for entertainment, restaurants...
- **Market:**
    * Anyone who travels/is visiting an area they are not as familiar with can use this app
- **Habit:**
    * Users can use this app throughout the day when they go out 
    * Average user consumes the app
    * If user allow camera access, they can create gallery based on location
- **Scope:**
    * V1 allows user to find attractions nearby, sorted by type of place
    * V2 allows users to add their own photos to a gallery
    * V3 app suggests places to go based on user liking 
    * V4 Add price checking between places (hotels, transit)

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

- [x] User can select cities of their own choosing on a map
* User can find nearby food, tourist attractions, hotels, public transportation
* User can see nearby places by place type
* Clicking on a place will show more information about it (e.g. distance, address, contact, atmosphere)

**Stretch Features**

* User can price compare between locations
* User can star locations into a personal list to look at later
* App suggests places based on user rating
* Add reviews for foods at restaurants
* Flight information
* User can search for nearby places by place type
* User can filter for places 
* Upload user photos to a gallery


### 2. Screen Archetypes

- Main Activity: Map activity
  - Pinpoint where to search for nearby attractions on a map
- City List Activity
- Tabview Activity
    - Attractions, Food, Hotel, Transportation List
      - Bottom navigation menu for attractions, food, hotels, and public transportation
      - User can see nearby locations corresponding to place type
    - Attraction Details
      - User can see basic attraction information for the location
    - Food Details
      - User can see basic restaurant information, menu
    - Hotel Details
      - User can see nearby hotels and their prices
      - Hotel list linked to google maps


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Tour Attractions Container: List of attractions: Place Details Activity
* Foods Container: List of restaurants: Food Details Activity
* Hotel Container: List of hotels: Hotel Details Activity
* Public Transportation Container: List of nearby transit: Transportation Details Activity

- Attractions List
  - Food List
  - Hotel List
  - Transportation List
- Food List
  - Attractions List
  - Hotel List
  - Transportation List
- Hotel List
  - Attractions List
  - Food List
  - Transportation List
- Transportation List
  - Attractions List
  - Hotel List
  - Food List

**Flow Navigation** (Screen to Screen)
    
- Main Activity - City List
  - Map Page (Pinpoint Locations)
  - Attractions List 
- Map Activity
  - City List
- Tabview Activity

- Attractions List
  - Attraction Details
  - City List
- Food List
  - Food Details
  - City List
- Hotel List
  - Hotel Details
  - City List
 
## Video Walkthrough

Walkthrough of adding cities: 

<img src='TraversityDemo1.gif' title='Traversity Demo' height='600' alt='Traversity App Demo' />
GIF created with [LiceCap](http://www.cockos.com/licecap/).  

Full Demo: [Demo Link](https://github.com/KotlinTeam15/Travel/blob/master/Screen_Recording_20221112-132004_Travel%20App.mp4)

## Wireframes

<img src="https://github.com/KotlinTeam15/Travel/blob/master/Travel%20Wireframe-1_7ea.png" width=600>
