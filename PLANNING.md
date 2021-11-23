Food Truck Review App

Process for build:

Monolithic app using H2 DB and transition to Postgres

Overall idea and structure Build a food truck review app for different users to be able to rate different food trucks 3
roles would be Admin, Truck owner and Customer -- Admin can delete posts, or suspend users/truck owners -- Truck owners
can add different options such as listing themselves hours, menu, locations and be able to reply to comments --
Customers can rate food trucks, leave comments, search for food trucks by category or name (by location) G

Databases

Postgres will be used for:
User, Admin and Truck owner profiles, profile status and details

MongoDB will be used for:
comments ratings truck profile menu, locations, hours of operation, truck owner story, item status, images

Admin:
User Stories As an admin I want to be able to delete posts and reviews for malicious/inappropriate content As an admin I
want to be able to suspend users for malicious/inappropriate content As an admin I want to be able to suspend truck
owners for malicious/inappropriate content As an admin I want to be able to suspend truck owners due to inactivity. As
an admin I want to be able to verify truck owner status (G)

User Stories Customer As a user I want to be able to create an account As a user I want to be able to search for food
trucks by name, category or city. As a user I want to be able to view the food truck's details As a user I want to be
able to leave comments/feedback for the food truck owner. As a user I want to be able to leave a rating for the food
truck. As a user I want to be able to update my review. As a user I want to be able to edit my profile As a user I want
to be able to view my previous reviews As a user I want to be able to delete my comment/review.

User Stories Truck Owner As a truck owner I want to be able to create a profile and add my business As a truck owner I
want to be able to add my food truck details (menu, hours, locations)
As a truck owner I want to be able to edit my business details As a truck owner I want to be able to delete my business
details As a truck owner I want to be able to view user comments for my truck. As a truck owner I want to be able to
respond to user comments about my truck. As a truck owner I want to be able to add images to my profile showing my food
truck/food items. As a truck owner I want to be able to upload my story to my profile.
