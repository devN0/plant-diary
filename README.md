# plant-diary


## Introduction

Plant Diary allows homeowners to maintain records on plants in their yard. They can upload photos and add notes to a plant at any time.  Plant data, including sustainability and edibility, are sourced from PlantPlaces.com master data.

Users can generate a report to show several attributes of their yard: sustainability, edibility, native, etc.  This report can be used to help sell the positive attributes of the house.

Users can interact with PlantDiary using either a set of RESTful service endpoints, or a simple UI, or both.

## Requirements

1. As a homeowner I want to catalog my specimens, so that I'll remember what I planted.

### Example

**Given**: A feed of plant data is available.

**When**: The user/service selects a plant Eastern Redbud.

**When**: The user/service adds latitude 39.74 to an Eastern Redbud specimen.

**Then**: The user's/service's Eastern Redbud specimen will be saved with latitude 39.74

### Example

**Given**: Specimen data is available.

**When**: The user/service searches for “kajsd;luaopuidfjo;aj;sd”

**Then**: Plant Diary will not return any results, and the user will not be able to save the specimen.

### Example

**Given**: Specimen data is available and specimen 83 is Eastern Redbud.

**When**: The user/specimen searches with id 83.

**Then**: Plant Diary will return exactly one record for "Eastern Redbud" specimen.
